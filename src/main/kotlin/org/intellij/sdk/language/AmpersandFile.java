package org.intellij.sdk.language;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class AmpersandFile extends PsiFileBase {

	protected AmpersandFile(@NotNull FileViewProvider viewProvider) {
		super(viewProvider, AmpersandLanguage.INSTANCE);
	}

	@Override
	public @NotNull FileType getFileType() {
		return AmpersandFileType.INSTANCE;
	}
}
