package org.intellij.sdk.language.minimessage.lexer;

import com.intellij.lexer.FlexAdapter;

public class MiniMessageLexerAdapter extends FlexAdapter {

	public MiniMessageLexerAdapter() {
		super(new MiniMessageLexer(null));
	}
}
