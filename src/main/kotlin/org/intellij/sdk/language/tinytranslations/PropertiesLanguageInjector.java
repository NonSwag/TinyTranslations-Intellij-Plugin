package org.intellij.sdk.language.tinytranslations;

import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.lang.injection.general.SimpleInjection;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.nanomessage.NanoMessageLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertiesLanguageInjector implements LanguageInjectionContributor, DumbAware {

	@Override
	public @Nullable Injection getInjection(@NotNull PsiElement psiElement) {
		String path = psiElement.getContainingFile().getOriginalFile().getVirtualFile().getPath();
		if (!path.contains("/lang/")) {
			return null;
		}
		if (psiElement instanceof Property) {
			return new SimpleInjection(NanoMessageLanguage.INSTANCE, "", "", null);
		}
		return null;
	}
}
