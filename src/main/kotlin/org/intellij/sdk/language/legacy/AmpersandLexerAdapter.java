package org.intellij.sdk.language.legacy;

import com.intellij.lexer.FlexAdapter;

public class AmpersandLexerAdapter extends FlexAdapter {

	public AmpersandLexerAdapter() {
		super(new AmpersandLexer(null));
	}
}
