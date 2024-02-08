package org.intellij.sdk.language.minimessage;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.intellij.sdk.language.TinyTranslationsIcons;
import org.intellij.sdk.language.nanomessage.NanoMessageLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MiniMessageFileType extends LanguageFileType {

	public static final MiniMessageFileType INSTANCE = new MiniMessageFileType();

	protected MiniMessageFileType() {
		super(MiniMessageLanguage.INSTANCE);
	}

	@Override
	public @NonNls @NotNull String getName() {
		return "MiniMessage";
	}

	@Override
	public @NlsContexts.Label @NotNull String getDescription() {
		return "Minecraft MiniMessage";
	}

	@Override
	public @NlsSafe @NotNull String getDefaultExtension() {
		return "mm";
	}

	@Override
	public Icon getIcon() {
		return TinyTranslationsIcons.Logo;
	}
}
