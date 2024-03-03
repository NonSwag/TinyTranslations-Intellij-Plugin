package org.intellij.sdk.language.nanomessage;

import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlText;
import org.intellij.sdk.language.minimessage.MiniMessageLanguage;

public class NanoMessageLanguage extends MiniMessageLanguage {

	public static final NanoMessageLanguage INSTANCE = new NanoMessageLanguage();

	protected NanoMessageLanguage(String name) {
		super(name);
	}

	protected NanoMessageLanguage() {
		this("NanoMessage");
	}
}
