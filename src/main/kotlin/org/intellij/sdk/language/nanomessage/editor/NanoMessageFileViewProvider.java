package org.intellij.sdk.language.nanomessage.editor;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiManager;
import org.intellij.sdk.language.minimessage.MiniMessageLanguage;
import org.intellij.sdk.language.nanomessage.NanoMessageLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class NanoMessageFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider {

    public NanoMessageFileViewProvider(@NotNull PsiManager manager, @NotNull VirtualFile virtualFile, boolean eventSystemEnabled) {
        super(manager, virtualFile, eventSystemEnabled);
    }

    @Override
    public @NotNull Language getBaseLanguage() {
        return MiniMessageLanguage.INSTANCE;
    }

    @Override
    public @NotNull Set<Language> getLanguages() {
        return Set.of(MiniMessageLanguage.INSTANCE, NanoMessageLanguage.INSTANCE);
    }

    @Override
    protected @NotNull MultiplePsiFilesPerDocumentFileViewProvider cloneInner(@NotNull VirtualFile virtualFile) {
        return new NanoMessageFileViewProvider(getManager(), virtualFile, false);
    }
}
