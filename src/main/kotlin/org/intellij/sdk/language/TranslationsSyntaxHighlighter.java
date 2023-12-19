package org.intellij.sdk.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.TranslationsTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class TranslationsSyntaxHighlighter extends SyntaxHighlighterBase {

	public static final TextAttributesKey SEPARATOR =
			createTextAttributesKey("TRANSLATIONS_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey KEY =
			createTextAttributesKey("TRANSLATIONS_KEY", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey ATTRIBUTE =
			createTextAttributesKey("TRANSLATIONS_ATTRIBUTE", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey BOOL =
			createTextAttributesKey("TRANSLATIONS_BOOL", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey PLACEHOLDER =
			createTextAttributesKey("TRANSLATIONS_PLACEHOLDER", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey NUMBER =
			createTextAttributesKey("TRANSLATIONS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BAD_CHARACTER =
			createTextAttributesKey("TRANSLATIONS_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

	private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
	private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
	private static final TextAttributesKey[] PLACEHOLDER_KEYS = new TextAttributesKey[]{PLACEHOLDER};
	private static final TextAttributesKey[] ATTRIBUTES_KEYS = new TextAttributesKey[]{ATTRIBUTE};
	private static final TextAttributesKey[] BOOL_KEYS = new TextAttributesKey[]{BOOL};
	private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
	private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
	private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


	@Override
	public @NotNull Lexer getHighlightingLexer() {
		return new TranslationsLexerAdapter();
	}

	@Override
	public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {

		if (tokenType.equals(TranslationsTypes.TAG_OPEN)
				|| tokenType.equals(TranslationsTypes.TAG_END)
				|| tokenType.equals(TranslationsTypes.TAG_CLOSE)
				|| tokenType.equals(TranslationsTypes.TAG_KEY)
		) {
			return KEY_KEYS;
		}
		if (tokenType.equals(TranslationsTypes.PH_OPEN)
				|| tokenType.equals(TranslationsTypes.PH_CLOSE)
				|| tokenType.equals(TranslationsTypes.PH_KEY)) {
			return PLACEHOLDER_KEYS;
		}
		if (tokenType.equals(TranslationsTypes.SEPARATOR)) {
			return SEPARATOR_KEYS;
		}
		if (tokenType.equals(TranslationsTypes.BOOL)) {
			return BOOL_KEYS;
		}
		if (tokenType.equals(TranslationsTypes.VALUE)) {
			return ATTRIBUTES_KEYS;
		}
		if (tokenType.equals(TranslationsTypes.NUMBER)) {
			return NUMBER_KEYS;
		}
		if (tokenType.equals(TokenType.BAD_CHARACTER)) {
			return BAD_CHAR_KEYS;
		}
		return EMPTY_KEYS;
	}
}
