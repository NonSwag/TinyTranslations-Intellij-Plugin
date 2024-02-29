package org.intellij.sdk.language.legacy.ampersand;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.intellij.sdk.language.TinyTranslationsIcons;
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
		return "MinecraftAmpersandFormatting";
	}

	@Override
	public @NlsContexts.Label @NotNull String getDescription() {
		return "Minecraft ampersand formatting";
	}

	@Override
	public @NlsSafe @NotNull String getDefaultExtension() {
		return "amp";
	}

	@Override
	public Icon getIcon() {
		return TinyTranslationsIcons.Logo;
	}
}
