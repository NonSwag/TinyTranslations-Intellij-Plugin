package org.intellij.sdk.language;

import com.intellij.lexer.FlexAdapter;

public class TranslationsLexerAdapter extends FlexAdapter {

	public TranslationsLexerAdapter() {
		super(new TranslationsLexer(null));
	}
}
