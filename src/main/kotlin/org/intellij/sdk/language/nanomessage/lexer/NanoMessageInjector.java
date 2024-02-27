package org.intellij.sdk.language.nanomessage.lexer;

import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.lang.injection.general.SimpleInjection;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.nanomessage.NanoMessageFileType;
import org.intellij.sdk.language.nanomessage.NanoMessageLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NanoMessageInjector implements LanguageInjectionContributor, DumbAware {

    @Override
    public @Nullable Injection getInjection(@NotNull PsiElement psiElement) {
        if (!psiElement.getContainingFile().getFileType().equals(NanoMessageFileType.INSTANCE)) {
            return null;
        }
        return new SimpleInjection(NanoMessageLanguage.INSTANCE, "", "", null);
    }
}
