package org.intellij.sdk.language.nanomessage.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.ProperTextRange;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.sdk.language.nanomessage.NanoMessageElementFactory;
import org.jetbrains.annotations.NotNull;

public abstract class NanoMessageInjectable extends ASTWrapperPsiElement implements PsiLanguageInjectionHost {

	public NanoMessageInjectable(@NotNull ASTNode node) {
		super(node);
	}

	@Override
	public boolean isValidHost() {
		return true;
	}

	@Override
	public PsiLanguageInjectionHost updateText(@NotNull String s) {
		return (PsiLanguageInjectionHost) this.replace(NanoMessageElementFactory.createText(getProject(), s));
	}

	@Override
	public @NotNull LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
		return new LiteralEscaper(this);
	}

	private static class LiteralEscaper extends LiteralTextEscaper<NanoMessageInjectable> {

		protected LiteralEscaper(@NotNull NanoMessageInjectable host) {
			super(host);
		}

		@Override
		public boolean decode(@NotNull final TextRange rangeInsideHost, @NotNull final StringBuilder outChars) {
			// todo implement proper java-like string escapes support
			ProperTextRange.assertProperRange(rangeInsideHost);
			outChars.append(myHost.getText(), rangeInsideHost.getStartOffset(), rangeInsideHost.getEndOffset());
			return true;
		}

		@Override
		public int getOffsetInHost(final int offsetInDecoded, @NotNull final TextRange rangeInsideHost) {
			ProperTextRange.assertProperRange(rangeInsideHost);
			int offset = offsetInDecoded;
			// todo implement proper java-like string escapes support
			offset += rangeInsideHost.getStartOffset();
			if (offset < rangeInsideHost.getStartOffset()) offset = rangeInsideHost.getStartOffset();
			if (offset > rangeInsideHost.getEndOffset()) offset = rangeInsideHost.getEndOffset();
			return offset;
		}

		@Override
		public boolean isOneLine() {
			return true;
		}
	}
}
