package org.intellij.sdk.language.nanomessage;

import com.intellij.lexer.FlexAdapter;
import org.intellij.sdk.language.NanoMessageLexer;

public class NanoMessageLexerAdapter extends FlexAdapter {

	public NanoMessageLexerAdapter() {
		super(new NanoMessageLexer(null));
	}
}
