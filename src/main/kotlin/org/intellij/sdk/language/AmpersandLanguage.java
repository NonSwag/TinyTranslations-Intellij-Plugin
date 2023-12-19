package org.intellij.sdk.language;

import com.intellij.lang.Language;

public class AmpersandLanguage extends Language {

	public static final AmpersandLanguage INSTANCE = new AmpersandLanguage();

	protected AmpersandLanguage() {
		super("MinecraftAmpersand");
	}
}
