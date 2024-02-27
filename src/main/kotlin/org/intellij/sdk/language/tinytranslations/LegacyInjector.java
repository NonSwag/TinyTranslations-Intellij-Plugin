package org.intellij.sdk.language.tinytranslations;

import com.intellij.json.JsonLanguage;
import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlText;
import org.intellij.sdk.language.legacy.AmpersandLanguage;
import org.intellij.sdk.language.nanomessage.NanoMessageFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class LegacyInjector implements MultiHostInjector {

	@Override
	public void getLanguagesToInject(@NotNull MultiHostRegistrar multiHostRegistrar, @NotNull PsiElement context) {
		if (!context.getContainingFile().getFileType().equals(NanoMessageFileType.INSTANCE)) {
			return;
		}
		if (context instanceof XmlTag tag) {
			if (tag.getChildren()[1] instanceof XmlText text) {
				Language language = switch (tag.getName()) {
					case "legacy" -> AmpersandLanguage.INSTANCE;
					case "json", "nbt", "gson" -> JsonLanguage.INSTANCE;
					default -> null;
				};
				if (language != null) {
					multiHostRegistrar
							.startInjecting(language)
							//.addPlace(null, null, text, tag.getTextRangeInParent())
							.doneInjecting();
				}
			}
		}
	}

	@Override
	public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
		return Collections.emptyList();//List.of(NanoMessageContentTag.class);
	}
}
