package org.intellij.sdk.language.nanomessage;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NanoMessageTagReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public NanoMessageTagReference(@NotNull PsiElement element, TextRange rangeInElement) {
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
		if (myElement.getParent() instanceof NanoMessageContentTag contentTag) {
			return contentTag.getOpenTag().getKey();
		}
		return null;
	}
}
