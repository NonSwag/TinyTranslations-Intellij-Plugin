package org.intellij.sdk.language.nanomessage;

import com.intellij.codeInsight.highlighting.CodeBlockSupportHandler;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageKey;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class NanoMessageCodeBlockSupportHandler implements CodeBlockSupportHandler, DumbAware {
	@Override
	public @NotNull List<TextRange> getCodeBlockMarkerRanges(@NotNull PsiElement psiElement) {
		if (psiElement.getParent() instanceof NanoMessageKey key) {
			if (key.getParent().getParent() instanceof NanoMessageContentTag contentTag) {
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
		if (psiElement instanceof NanoMessageContentTag tag) {
			return tag.getTextRange();
		}
		return TextRange.EMPTY_RANGE;
	}
}
