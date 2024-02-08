package org.intellij.sdk.language.nanomessage;

import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;

public class NanoMessageLanguage extends Language {

	public static final NanoMessageLanguage INSTANCE = new NanoMessageLanguage();

	protected NanoMessageLanguage(String name) {
		super(XMLLanguage.INSTANCE, name);
	}

	protected NanoMessageLanguage() {
		this("NanoMessage");
	}
}
