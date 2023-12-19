package org.intellij.sdk.language;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import org.jetbrains.annotations.NotNull;

public class AmpersandLexerAdapter extends FlexAdapter {

	public AmpersandLexerAdapter() {
		super(new AmpersandLexer(null));
	}
}
