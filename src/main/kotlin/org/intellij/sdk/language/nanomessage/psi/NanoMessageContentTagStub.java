package org.intellij.sdk.language.nanomessage.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.sdk.language.nanomessage.psi.impl.NanoMessageContentTagImpl;
import org.jetbrains.annotations.NotNull;

public abstract class NanoMessageContentTagStub implements PsiLanguageInjectionHost {

	public boolean isValidHost() {
		return true;
	}

	public PsiLanguageInjectionHost updateText(@NotNull String var1) {
		return this;
	}

	@NotNull
	public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
		return new LiteralTextEscaper<PsiLanguageInjectionHost>(this) {
			@Override
			public boolean decode(@NotNull TextRange textRange, @NotNull StringBuilder stringBuilder) {
				return true;
			}

			@Override
			public int getOffsetInHost(int i, @NotNull TextRange textRange) {
				return 0;
			}

			@Override
			public boolean isOneLine() {
				return false;
			}
		};
	}
}
