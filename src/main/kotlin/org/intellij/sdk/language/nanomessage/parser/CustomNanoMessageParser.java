package org.intellij.sdk.language.nanomessage.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static com.intellij.psi.xml.XmlElementType.*;
import static com.intellij.psi.xml.XmlTokenType.*;
import static org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes.*;

public class CustomNanoMessageParser implements PsiParser, LightPsiParser {

	private static final List<String> PRE = List.of("pre", "nbt", "json", "gson", "legacy");
	private boolean isNanoMessage;

	public CustomNanoMessageParser(boolean miniMessageOnly) {
		this.isNanoMessage = !miniMessageOnly;
	}

	@Override
	public @NotNull ASTNode parse(@NotNull IElementType iElementType, @NotNull PsiBuilder psiBuilder) {
		parseContents(psiBuilder, 0, b -> true);
		return psiBuilder.getTreeBuilt();
	}

	@Override
	public void parseLight(IElementType iElementType, PsiBuilder psiBuilder) {
		parseContents(psiBuilder, 0, b -> true);
		psiBuilder.getLightTree();
	}

	private boolean parseContents(PsiBuilder b, int l, Function<PsiBuilder, Boolean> predicate) {
		Marker m = b.mark();
		boolean ret = true;
		while (b.getTokenType() != null && predicate.apply(b)) {
			boolean parsedAnything = parseContentTag(b, l + 1)
					|| parseSelfClosingTag(b, l + 1)
					|| (isNanoMessage && parseChoice(b, l + 1))
					|| (isNanoMessage && parsePlaceholder(b, l + 1))
					|| parseText(b, l + 1);
			if (!parsedAnything) {
				b.advanceLexer();
			}
			ret = ret && parsedAnything;
		}
		m.done(CONTENTS);
		return ret;
	}

	private boolean parsePlaceholder(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!consumeTokens(b, PH_OPEN)) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!(consumeTokens(b, LITERAL) && parseAttributes(b, l + 1))) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!consumeTokens(b, PH_CLOSE)) {
			return fail(m);
		}
		m.done(PLACEHOLDER);
		return true;
	}

	private boolean parseChoice(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!consumeTokens(b, PH_OPEN)) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!(consumeTokens(b, LITERAL) && parseAttributes(b, l + 1))) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!consumeTokens(b, CHOICE)) {
			return fail(m);
		}
		boolean expectAttribute = true;
		while (expectAttribute) {
			consumeWhiteSpaces(b);
			Marker opt = b.mark();
			if (!(parseString(b))) {
				if (!parseContents(b, l + 1, bx -> !is(bx, SEPARATOR) && !is(bx, PH_CLOSE))) {
					return fail(m);
				}
			}
			consumeWhiteSpaces(b);
			opt.done(CHOICE_OPTION);
			if (!consumeTokens(b, SEPARATOR)) {
				expectAttribute = false;
			}
			consumeWhiteSpaces(b);
		}
		if (!consumeTokens(b, PH_CLOSE)) {
			return fail(m);
		}
		m.done(CHOICE_PLACEHOLDER);
		return true;
	}

	private boolean parseSelfClosingTag(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!(consumeTokens(b, XML_START_TAG_START) && parseTagKey(b) && parseAttributes(b, l + 1))) {
			return fail(m);
		}
		if (!consumeTokens(b, XML_EMPTY_ELEMENT_END)) {
			return fail(m);
		}
		m.done(XML_TAG);
		return true;
	}

	private boolean parseContentTag(PsiBuilder b, int l) {
		Marker m = b.mark();

		String open = parseOpenTag(b, l + 1);
		if (open == null) {
			return fail(m);
		}
		if (isNanoMessage && PRE.stream().anyMatch(open::equalsIgnoreCase)) {
			parsePreContent(b, open);
		} else {
			parseContents(b, l + 1, bx -> !isCloseTag(bx, open));
		}
		parseCloseTag(b, open);
		m.done(XML_TAG);
		return true;
	}

	private boolean parsePreContent(PsiBuilder b, String open) {
		Marker m = b.mark();
		while (!isCloseTag(b, open) && b.getTokenType() != null) {
			b.advanceLexer();
		}
		m.done(TEXT_ELEMENT);
		return true;
	}

	private @Nullable String parseOpenTag(PsiBuilder b, int l) {
		if (!consumeTokens(b, XML_START_TAG_START)) {
			return null;
		}
		String startTag = b.getTokenText();
		if (!(parseTagKey(b) && parseAttributes(b, l + 1) && consumeTokens(b, XML_TAG_END))) {
			return null;
		}
		return startTag;
	}

	private boolean isCloseTag(PsiBuilder b, String openTag) {
		Marker m = b.mark();
		boolean result = consumeTokens(b, XML_END_TAG_START)
				&& Objects.equals(b.getTokenText(), openTag)
				&& consumeTokens(b, XML_TAG_NAME, XML_TAG_END);
		m.rollbackTo();
		return result;
	}

	private boolean parseCloseTag(PsiBuilder b, String openTag) {
		if (!consumeTokens(b, XML_END_TAG_START)) {
			return false;
		}
		String endTag = b.getTokenText();
        return parseTagKey(b) && Objects.equals(openTag, endTag) && consumeTokens(b, XML_TAG_END);
    }

	private boolean parseText(PsiBuilder b, int l) {
		if (b.getTokenType() == null) {
			return false;
		}
		return consumeTokens(b, b.getTokenType());
//		Marker m = b.mark();
//		if (!consumeTokens(b, b.getTokenType())) {
//			return fail(m);
//		}
//		m.done(TEXT_ELEMENT);
//		return true;
	}

	private boolean parseAttributes(PsiBuilder b, int l) {
		Marker m = b.mark();
		while (true) {
			if (!consumeTokens(b, SEPARATOR)) {
				m.done(ATTRIBUTES);
				return true;
			}
			if (!parseAttribute(b, l + 1)) {
				return fail(m);
			}
		}
	}

	private final List<IElementType> delimitAttribute = List.of(XML_START_TAG_START, SEPARATOR, XML_TAG_END, PH_CLOSE, CHOICE, XML_EMPTY_ELEMENT_END, XML_END_TAG_START);
	private boolean parseAttribute(PsiBuilder b, int l) {
		Marker m = b.mark();

		if (!parseString(b)) {
			m.rollbackTo();
			m = b.mark();
			while (!is(b, delimitAttribute)) {
				b.advanceLexer();
				if (b.getTokenType() == null) {
					return fail(m);
				}
			}
		}
		m.done(ATTRIBUTE);
		return true;
	}

	private boolean parseTagKey(PsiBuilder b) {
        return consumeTokens(b, XML_TAG_NAME);
    }

	private boolean parseString(PsiBuilder b) {
		Marker m = b.mark();
		boolean r = false;
		if (consumeTokens(b, SQUOTE)) {
			while (!(is(b, SQUOTE))) {
				b.advanceLexer();
				if (b.getTokenType() == null) {
					m.rollbackTo();
					return false;
				}
			}
			r = consumeTokens(b, SQUOTE);
		} else if (consumeTokens(b, DQUOTE)) {
			while (!(is(b, DQUOTE))) {
				b.advanceLexer();
				if (b.getTokenType() == null) {
					m.rollbackTo();
					return false;
				}
			}
			r = consumeTokens(b, DQUOTE);
		}
		if (!r) {
			m.rollbackTo();
			return false;
		}
		m.done(TEXT_ELEMENT);
		return true;
	}

	private boolean consumeWhiteSpaces(PsiBuilder b) {
		boolean val = false;
		while (consumeTokens(b, XML_WHITE_SPACE)) {
			val = true;
		}
		return val;
	}

	private boolean consumeTokens(PsiBuilder b, IElementType... types) {
		for (IElementType type : types) {

			if (!Objects.equals(b.getTokenType(), type)) {
				return false;
			}
			b.advanceLexer();
		}
		return true;
	}

	private boolean nextIs(PsiBuilder builder, IElementType type) {
		return Objects.equals(builder.lookAhead(1), type);
	}

	private boolean nextIs(PsiBuilder builder, Iterable<IElementType> types) {
		for (IElementType type : types) {
			if (nextIs(builder, type)) {
				return true;
			}
		}
		return false;
	}

	private boolean is(PsiBuilder builder, IElementType type) {
		return Objects.equals(builder.getTokenType(), type);
	}

	private boolean is(PsiBuilder builder, Iterable<IElementType> types) {
		for (IElementType type : types) {
			if (is(builder, type)) {
				return true;
			}
		}
		return false;
	}

	private boolean fail(Marker m) {
		m.rollbackTo();
		return false;
	}
}
