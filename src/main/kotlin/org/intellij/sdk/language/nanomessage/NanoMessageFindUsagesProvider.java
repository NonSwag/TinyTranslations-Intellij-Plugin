package org.intellij.sdk.language.nanomessage;

import com.intellij.lang.HelpID;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NanoMessageFindUsagesProvider implements FindUsagesProvider {

	@Override
	public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
		return psiElement instanceof PsiNamedElement;
	}

	@Override
	public @Nullable @NonNls String getHelpId(@NotNull PsiElement psiElement) {
		return HelpID.FIND_OTHER_USAGES;
	}

	@Override
	public @Nls @NotNull String getType(@NotNull PsiElement psiElement) {
		return psiElement.getText();
	}

	@Override
	public @Nls @NotNull String getDescriptiveName(@NotNull PsiElement psiElement) {
		return ((PsiNamedElement) psiElement).getName();
	}

	@Override
	public @Nls @NotNull String getNodeText(@NotNull PsiElement psiElement, boolean b) {
		return psiElement.getText();
	}
}
