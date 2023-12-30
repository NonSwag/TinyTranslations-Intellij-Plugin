package org.intellij.sdk.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.psi.TranslationsCloseTag;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.intellij.sdk.language.psi.TranslationsKey;
import org.intellij.sdk.language.psi.TranslationsTypes;
import org.jetbrains.annotations.NotNull;

public class TranslationsReferenceContributor extends PsiReferenceContributor {
	@Override
	public void registerReferenceProviders(@NotNull PsiReferenceRegistrar psiReferenceRegistrar) {
		psiReferenceRegistrar.registerReferenceProvider(PlatformPatterns.psiElement(TranslationsTypes.KEY), new PsiReferenceProvider() {
			@Override
			public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement psiElement, @NotNull ProcessingContext processingContext) {
				if (psiElement instanceof TranslationsKey && psiElement.getParent() instanceof TranslationsCloseTag closeTag) {
					TranslationsContentTag contentTag = (TranslationsContentTag) closeTag.getParent();
					return new PsiReference[]{ new TranslationsTagReference(closeTag, TextRange.allOf(closeTag.getText())) };
				}
				return PsiReference.EMPTY_ARRAY;
			}
		});

	}
}
