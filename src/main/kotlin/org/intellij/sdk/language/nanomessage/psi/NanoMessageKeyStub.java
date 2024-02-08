package org.intellij.sdk.language.nanomessage.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.intellij.sdk.language.nanomessage.NanoMessageElementFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NanoMessageKeyStub extends ASTWrapperPsiElement implements NanoMessageKey {

	public NanoMessageKeyStub(@NotNull ASTNode node) {
		super(node);
	}

	@Override
	public @Nullable PsiElement getIdentifyingElement() {
		return this;
	}

	@Override
	public @Nullable PsiElement getNameIdentifier() {
		return this;
	}

	@Override
	public String getName() {
		return super.getText();
	}

	@Override
	public PsiElement setName(@NlsSafe @NotNull String s) throws IncorrectOperationException {
		return replace(NanoMessageElementFactory.createKey(getProject(), s));
	}
}
