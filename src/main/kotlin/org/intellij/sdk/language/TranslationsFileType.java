package org.intellij.sdk.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TranslationsFileType extends LanguageFileType {

	public static final TranslationsFileType INSTANCE = new TranslationsFileType();

	protected TranslationsFileType() {
		super(TranslationsLanguage.INSTANCE);
	}

	@Override
	public @NonNls @NotNull String getName() {
		return "MinecraftTranslations";
	}

	@Override
	public @NlsContexts.Label @NotNull String getDescription() {
		return "A file for styled minecraft translations";
	}

	@Override
	public @NlsSafe @NotNull String getDefaultExtension() {
		return "";
	}

	@Override
	public Icon getIcon() {
		return null;
	}
}
