package org.intellij.sdk.language.nanomessage;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageCloseTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageKey;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes;
import org.jetbrains.annotations.NotNull;

public class NanoMessageReferenceContributor extends PsiReferenceContributor {
	@Override
	public void registerReferenceProviders(@NotNull PsiReferenceRegistrar psiReferenceRegistrar) {
		psiReferenceRegistrar.registerReferenceProvider(PlatformPatterns.psiElement(NanoMessageTypes.KEY), new PsiReferenceProvider() {
			@Override
			public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement psiElement, @NotNull ProcessingContext processingContext) {
				if (psiElement instanceof NanoMessageKey && psiElement.getParent() instanceof NanoMessageCloseTag closeTag) {
					NanoMessageContentTag contentTag = (NanoMessageContentTag) closeTag.getParent();
					return new PsiReference[]{ new NanoMessageTagReference(closeTag, TextRange.allOf(closeTag.getText())) };
				}
				return PsiReference.EMPTY_ARRAY;
			}
		});

	}
}
