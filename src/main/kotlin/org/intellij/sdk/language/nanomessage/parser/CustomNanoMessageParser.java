package org.intellij.sdk.language.nanomessage.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes.*;

public class CustomNanoMessageParser implements PsiParser, LightPsiParser {

	private static final List<String> PRE = List.of("pre", "nbt", "json", "gson", "legacy");

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

	private static boolean parseContents(PsiBuilder b, int l, Function<PsiBuilder, Boolean> predicate) {
		Marker m = b.mark();
		boolean ret = true;
		while (b.getTokenType() != null && predicate.apply(b)) {
			boolean parsedAnything = parseContentTag(b, l + 1)
					|| parseSelfClosingTag(b, l + 1)
					|| parseChoice(b, l + 1)
					|| parsePlaceholder(b, l + 1)
					|| parseText(b, l + 1);
			if (!parsedAnything) {
				b.advanceLexer();
			}
			ret = ret && parsedAnything;
		}
		m.done(CONTENTS);
		return ret;
	}

	private static boolean parsePlaceholder(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!consumeTokens(b, PH_OPEN)) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!(parseKey(b) && parseAttributes(b, l + 1))) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!consumeTokens(b, PH_CLOSE)) {
			return fail(m);
		}
		m.done(PLACEHOLDER);
		return true;
	}

	private static boolean parseChoice(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!consumeTokens(b, PH_OPEN)) {
			return fail(m);
		}
		consumeWhiteSpaces(b);
		if (!(parseKey(b) && parseAttributes(b, l + 1))) {
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

	private static boolean parseSelfClosingTag(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!(consumeTokens(b, TAG_OPEN) && parseKey(b) && parseAttributes(b, l + 1))) {
			return fail(m);
		}
		if (!consumeTokens(b, TAG_END, TAG_CLOSE)) {
			return fail(m);
		}
		m.done(SELF_CLOSING_TAG);
		return true;
	}

	private static boolean parseContentTag(PsiBuilder b, int l) {
		Marker m = b.mark();

		String open = parseOpenTag(b, l + 1);
		if (open == null) {
			return fail(m);
		}
		if (PRE.stream().anyMatch(open::equalsIgnoreCase)) {
			parsePreContent(b, open);
		} else {
			parseContents(b, l + 1, bx -> !isCloseTag(bx, open));
		}
		parseCloseTag(b, open);
		m.done(CONTENT_TAG);
		return true;
	}

	private static boolean parsePreContent(PsiBuilder b, String open) {
		Marker m = b.mark();
		while (!isCloseTag(b, open) && b.getTokenType() != null) {
			b.advanceLexer();
		}
		m.done(TEXT_ELEMENT);
		return true;
	}

	private static @Nullable String parseOpenTag(PsiBuilder b, int l) {
		Marker m = b.mark();
		if (!consumeTokens(b, TAG_OPEN)) {
			m.rollbackTo();
			return null;
		}
		String startTag = b.getTokenText();
		if (!(parseKey(b) && parseAttributes(b, l + 1) && consumeTokens(b, TAG_CLOSE))) {
			m.rollbackTo();
			return null;
		}
		m.done(OPEN_TAG);
		return startTag;
	}

	private static boolean isCloseTag(PsiBuilder b, String openTag) {
		Marker m = b.mark();
		boolean result = consumeTokens(b, TAG_OPEN, TAG_END)
				&& Objects.equals(b.getTokenText(), openTag)
				&& consumeTokens(b, LITERAL, TAG_CLOSE);
		m.rollbackTo();
		return result;
	}

	private static boolean parseCloseTag(PsiBuilder b, String openTag) {
		Marker m = b.mark();
		if (!consumeTokens(b, TAG_OPEN, TAG_END)) {
			return fail(m);
		}
		String endTag = b.getTokenText();
		if (!parseKey(b) || !Objects.equals(openTag, endTag) || !consumeTokens(b, TAG_CLOSE)) {
			return fail(m);
		}
		m.done(CLOSE_TAG);
		return true;
	}

	private static boolean parseText(PsiBuilder b, int l) {
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

	private static boolean parseAttributes(PsiBuilder b, int l) {
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

	private static final List<IElementType> delimitAttribute = List.of(TAG_OPEN, SEPARATOR, TAG_CLOSE, PH_CLOSE, CHOICE);
	private static boolean parseAttribute(PsiBuilder b, int l) {
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

	private static boolean parseKey(PsiBuilder b) {
		Marker m = b.mark();
		if (!consumeTokens(b, LITERAL)) {
			return fail(m);
		}
		m.done(KEY);
		return true;
	}

	private static boolean parseString(PsiBuilder b) {
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

	private static boolean consumeWhiteSpaces(PsiBuilder b) {
		boolean val = false;
		while (consumeTokens(b, WHITESPACE)) {
			val = true;
		}
		return val;
	}

	private static boolean consumeTokens(PsiBuilder b, IElementType... types) {
		for (IElementType type : types) {

			if (!Objects.equals(b.getTokenType(), type)) {
				return false;
			}
			b.advanceLexer();
		}
		return true;
	}

	private static boolean nextIs(PsiBuilder builder, IElementType type) {
		return Objects.equals(builder.lookAhead(1), type);
	}

	private static boolean nextIs(PsiBuilder builder, Iterable<IElementType> types) {
		for (IElementType type : types) {
			if (nextIs(builder, type)) {
				return true;
			}
		}
		return false;
	}

	private static boolean is(PsiBuilder builder, IElementType type) {
		return Objects.equals(builder.getTokenType(), type);
	}

	private static boolean is(PsiBuilder builder, Iterable<IElementType> types) {
		for (IElementType type : types) {
			if (is(builder, type)) {
				return true;
			}
		}
		return false;
	}

	private static boolean fail(Marker m) {
		m.rollbackTo();
		return false;
	}
}
