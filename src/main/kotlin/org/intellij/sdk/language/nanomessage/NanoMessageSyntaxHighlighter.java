package org.intellij.sdk.language.nanomessage;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class NanoMessageSyntaxHighlighter extends SyntaxHighlighterBase {

	public static final TextAttributesKey SEPARATOR =
			createTextAttributesKey("TRANSLATIONS_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey ATTRIBUTE =
			createTextAttributesKey("TRANSLATIONS_ATTRIBUTE", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey TAG =
			createTextAttributesKey("TRANSLATIONS_TAG", color(new Color(0xD5B778)));
	public static final TextAttributesKey PLACEHOLDER =
			createTextAttributesKey("TRANSLATIONS_PLACEHOLDER", color(new Color(0x56A8F5)));
	public static final TextAttributesKey CHOICE =
			createTextAttributesKey("TRANSLATIONS_CHOICE", color(new Color(0x6E7FF5)));

	private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
	private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{TAG};
	private static final TextAttributesKey[] PLACEHOLDER_KEYS = new TextAttributesKey[]{PLACEHOLDER};
	private static final TextAttributesKey[] ATTRIBUTES_KEYS = new TextAttributesKey[]{ATTRIBUTE};
	private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

	private static TextAttributes color(Color color) {
		TextAttributes attributes = new TextAttributes();
		attributes.setForegroundColor(color);
		return attributes;
	}

	@Override
	public @NotNull Lexer getHighlightingLexer() {
		return new NanoMessageLexerAdapter();
	}

	@Override
	public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
		return EMPTY_KEYS;
	}
}
