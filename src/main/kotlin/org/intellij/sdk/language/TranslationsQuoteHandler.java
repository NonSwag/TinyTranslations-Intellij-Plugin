package org.intellij.sdk.language;

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;
import org.intellij.sdk.language.psi.TranslationsTypes;

public class TranslationsQuoteHandler extends SimpleTokenSetQuoteHandler {

	public TranslationsQuoteHandler() {
		super(TranslationsTypes.LITERAL, TranslationsTypes.TEXT_ELEMENT);
	}
}
