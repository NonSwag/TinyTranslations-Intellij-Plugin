package org.intellij.sdk.language.tinytranslations;

import com.intellij.json.JsonLanguage;
import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.lang.injection.general.SimpleInjection;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.intellij.sdk.language.legacy.AmpersandLanguage;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LegacyInjector implements MultiHostInjector {

	@Override
	public void getLanguagesToInject(@NotNull MultiHostRegistrar multiHostRegistrar, @NotNull PsiElement context) {
		if (context instanceof NanoMessageContentTag tag) {
			if (tag.getChildren()[1] instanceof NanoMessageTextElement text) {
				Language language = switch (tag.getOpenTag().getKey().getText()) {
					case "legacy" -> AmpersandLanguage.INSTANCE;
					case "json", "nbt", "gson" -> JsonLanguage.INSTANCE;
					default -> null;
				};
				;
				if (language != null) {
					multiHostRegistrar
							.startInjecting(language)
							.addPlace(null, null, tag, text.getTextRangeInParent())
							.doneInjecting();
				}
			}
		}
	}

	@Override
	public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
		return List.of(NanoMessageContentTag.class);
	}
}
