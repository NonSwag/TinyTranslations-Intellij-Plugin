package org.intellij.sdk.language;

import com.intellij.lang.Language;

public class TranslationsLanguage extends Language {

	public static final TranslationsLanguage INSTANCE = new TranslationsLanguage();

	private TranslationsLanguage() {
		super("TranslationsFormat");
	}
}
