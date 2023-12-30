package org.intellij.sdk.language;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.hints.InlayInfo;
import com.intellij.codeInsight.hints.InlayParameterHintsProvider;
import com.intellij.codeInsight.hints.declarative.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.sdk.language.psi.TranslationsChoiceOption;
import org.intellij.sdk.language.psi.TranslationsChoicePlaceholder;
import org.intellij.sdk.language.psi.impl.TranslationsChoiceOptionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TranslationsChoiceInlayHintProvider implements InlayParameterHintsProvider {

	@Override
	public @NotNull List<InlayInfo> getParameterHints(@NotNull PsiElement element) {
		if (!(element instanceof TranslationsChoicePlaceholder choice) || choice.getChoiceOptionList().isEmpty()) {
			return Collections.emptyList();
		}
		if (choice.getChoiceOptionList().size() == 1) {
			return List.of(new InlayInfo("1|true", choice.getChoiceOptionList().get(0).getTextOffset()));
		}
		if (choice.getChoiceOptionList().size() == 2) {
			return List.of(
					new InlayInfo("1|true", choice.getChoiceOptionList().get(0).getTextOffset()),
					new InlayInfo("0|false|many", choice.getChoiceOptionList().get(1).getTextOffset())
			);
		}
		List<InlayInfo> infos = new ArrayList<>();
		int i = 0;
		for (TranslationsChoiceOption option : choice.getChoiceOptionList()) {
			if (i == 0) {
				infos.add(new InlayInfo("0|false", option.getTextOffset()));
			} else if (i == 1) {
				infos.add(new InlayInfo("1|true", option.getTextOffset()));
			} else if (i == choice.getChoiceOptionList().size() - 1) {
				infos.add(new InlayInfo("many", option.getTextOffset()));
			} else {
				infos.add(new InlayInfo("" + i, option.getTextOffset()));
			}
			i++;
		}
		return infos;
	}

	@Override
	public @NotNull Set<String> getDefaultBlackList() {
		return Collections.emptySet();
	}
}
