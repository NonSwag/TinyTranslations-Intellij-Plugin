package org.intellij.sdk.language.nanomessage.highlight;

import com.intellij.codeInsight.hints.InlayInfo;
import com.intellij.codeInsight.hints.InlayParameterHintsProvider;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.nanomessage.psi.NanoMessagePlaceholder;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NanoMessageChoiceInlayHintProvider implements InlayParameterHintsProvider {

	@Override
	public @NotNull List<InlayInfo> getParameterHints(@NotNull PsiElement element) {
		if (!(element instanceof NanoMessagePlaceholder choice) || !choice.hasAttributes()) {
			return Collections.emptyList();
		}
		if (choice.getAttributeCount() == 1) {
			return List.of(new InlayInfo("1|true", choice.getAttribute(0).getTextOffset()));
		}
		if (choice.getAttributeCount() == 2) {
			return List.of(
					new InlayInfo("1|true", choice.getAttribute(0).getTextOffset()),
					new InlayInfo("0|false|many", choice.getAttribute(1).getTextOffset())
			);
		}
		List<InlayInfo> infos = new ArrayList<>();
		int i = 0;
		for (PsiElement option : choice.getAttributes()) {
			if (i == 0) {
				infos.add(new InlayInfo("0|false", option.getTextOffset()));
			} else if (i == 1) {
				infos.add(new InlayInfo("1|true", option.getTextOffset()));
			} else if (i == choice.getAttributeCount() - 1) {
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
