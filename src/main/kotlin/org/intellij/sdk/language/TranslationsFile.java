package org.intellij.sdk.language;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class TranslationsFile extends PsiFileBase {

	protected TranslationsFile(@NotNull FileViewProvider viewProvider) {
		super(viewProvider, TranslationsLanguage.INSTANCE);
	}

	@Override
	public @NotNull FileType getFileType() {
		return TranslationsFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return "Translations File";
	}
}
