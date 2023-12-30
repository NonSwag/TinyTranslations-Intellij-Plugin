package org.intellij.sdk.language;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.intellij.sdk.language.psi.TranslationsCloseTag;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.intellij.sdk.language.psi.TranslationsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TranslationsTagReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TranslationsTagReference(@NotNull PsiElement element, TextRange rangeInElement) {
		super(element, rangeInElement);
	}

	@Override
	public ResolveResult @NotNull [] multiResolve(boolean b) {

		PsiElement el = resolve();
		if (el != null) {
			return new ResolveResult[]{ new PsiElementResolveResult(el) };
		}
		return new ResolveResult[0];
	}

	@Override
	public @Nullable PsiElement resolve() {
		if (myElement.getParent() instanceof TranslationsContentTag contentTag) {
			return contentTag.getOpenTag().getKey();
		}
		return null;
	}
}
