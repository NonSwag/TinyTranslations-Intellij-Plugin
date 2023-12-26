package org.intellij.sdk.language;

import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertiesLanguageInjector implements LanguageInjectionContributor {

	@Override
	public @Nullable Injection getInjection(@NotNull PsiElement psiElement) {
		return null;
	}
}
