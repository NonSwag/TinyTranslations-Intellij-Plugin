package org.intellij.sdk.language.nanomessage;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import org.intellij.sdk.language.nanomessage.editor.NanoMessageFileViewProvider;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class NanoMessageFile extends PsiFileBase {

	public NanoMessageFile(@NotNull FileViewProvider viewProvider) {
		super(new NanoMessageFileViewProvider(viewProvider.getManager(), viewProvider.getVirtualFile(), true), NanoMessageLanguage.INSTANCE);
	}

	@Override
	public @NotNull FileType getFileType() {
		return NanoMessageFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return "NanoMessage File";
	}
}
