package org.intellij.sdk.language.nanomessage;

import com.intellij.lang.Language;

public class NanoMessageLanguage extends Language {

	public static final NanoMessageLanguage INSTANCE = new NanoMessageLanguage();

	private NanoMessageLanguage() {
		super("NanoMessage");
	}
}
