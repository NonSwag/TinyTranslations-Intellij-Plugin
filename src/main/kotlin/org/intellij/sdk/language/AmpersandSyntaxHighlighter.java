package org.intellij.sdk.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.AmpersandTypes;
import org.intellij.sdk.language.psi.TranslationsTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class AmpersandSyntaxHighlighter extends SyntaxHighlighterBase {

	public static final TextAttributesKey COLOR =
			createTextAttributesKey("AMP_FORMAT", DefaultLanguageHighlighterColors.KEYWORD);

	private static final TextAttributesKey[] COLOR_KEYS = new TextAttributesKey[]{COLOR};
	private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


	@Override
	public @NotNull Lexer getHighlightingLexer() {
		return new TranslationsLexerAdapter();
	}

	@Override
	public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
		if (tokenType.equals(AmpersandTypes.FORMAT)) {
			return COLOR_KEYS;
		}
		return EMPTY_KEYS;
	}
}
