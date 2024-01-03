package org.intellij.sdk.language.nanomessage;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;

import static org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes.*;

public class NanoMessageQuoteHandler extends SimpleTokenSetQuoteHandler {

	public NanoMessageQuoteHandler() {
		super(LITERAL, TEXT_ELEMENT, MISC);
	}
}
