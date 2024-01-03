package org.intellij.sdk.language.nanomessage;

import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.sdk.language.legacy.AmpersandLanguage;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTextElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LegacyInjector implements MultiHostInjector {

	@Override
	public void getLanguagesToInject(@NotNull MultiHostRegistrar multiHostRegistrar, @NotNull PsiElement context) {
		if (context instanceof NanoMessageContentTag tag) {
			if (tag.getOpenTag().getKey().getText().equals("legacy") && tag.getContents() instanceof NanoMessageTextElement text) {
				multiHostRegistrar
						.startInjecting(AmpersandLanguage.INSTANCE)
						.addPlace("", "", tag, text.getTextRange())
						.doneInjecting();
			}
		}
	}

	@Override
	public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
		return List.of(NanoMessageTextElement.class);
	}
}
