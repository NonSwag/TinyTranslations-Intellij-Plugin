package org.intellij.sdk.language;

import com.intellij.codeInsight.highlighting.CodeBlockSupportHandler;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.intellij.sdk.language.psi.TranslationsKey;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class TranslationsCodeBlockSupportHandler implements CodeBlockSupportHandler, DumbAware {
	@Override
	public @NotNull List<TextRange> getCodeBlockMarkerRanges(@NotNull PsiElement psiElement) {
		if (psiElement.getParent() instanceof TranslationsKey key) {
			if (key.getParent().getParent() instanceof TranslationsContentTag contentTag) {
				if (contentTag.getCloseTag() != null) {
					return List.of(
							contentTag.getOpenTag().getTextRange(),
							contentTag.getCloseTag().getTextRange()
					);
				} else {
					return List.of(contentTag.getOpenTag().getTextRange());
				}
			}
		}
		return Collections.emptyList();
	}

	@Override
	public @NotNull TextRange getCodeBlockRange(@NotNull PsiElement psiElement) {
		if (psiElement instanceof TranslationsContentTag tag) {
			return tag.getTextRange();
		}
		return TextRange.EMPTY_RANGE;
	}
}
