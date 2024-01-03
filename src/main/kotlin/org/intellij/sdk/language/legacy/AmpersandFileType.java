package org.intellij.sdk.language.legacy;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AmpersandFileType extends LanguageFileType {

	public static final AmpersandFileType INSTANCE = new AmpersandFileType();

	protected AmpersandFileType() {
		super(AmpersandLanguage.INSTANCE);
	}

	@Override
	public @NonNls @NotNull String getName() {
		return "Ampersand";
	}

	@Override
	public @NlsContexts.Label @NotNull String getDescription() {
		return "Minecraft Ampersand";
	}

	@Override
	public @NlsSafe @NotNull String getDefaultExtension() {
		return "amp";
	}

	@Override
	public Icon getIcon() {
		return null;
	}
}
