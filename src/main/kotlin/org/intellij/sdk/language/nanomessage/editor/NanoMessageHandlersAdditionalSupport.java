package org.intellij.sdk.language.nanomessage.editor;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.XmlTypedHandlersAdditionalSupport;
import com.intellij.psi.PsiFile;
import org.intellij.sdk.language.nanomessage.NanoMessageLanguage;
import org.jetbrains.annotations.NotNull;

public class NanoMessageHandlersAdditionalSupport implements XmlTypedHandlersAdditionalSupport {
    @Override
    public boolean isAvailable(@NotNull PsiFile psiFile, @NotNull Language language) {
        return language.isKindOf(NanoMessageLanguage.INSTANCE);
    }
}
