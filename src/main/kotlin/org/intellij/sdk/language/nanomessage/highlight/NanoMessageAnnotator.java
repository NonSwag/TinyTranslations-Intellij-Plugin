package org.intellij.sdk.language.nanomessage.highlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessagePsiUtils;
import org.jetbrains.annotations.NotNull;

public class NanoMessageAnnotator implements Annotator, DumbAware {

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		if (psiElement instanceof XmlTag tag) {

			if (!NanoMessagePsiUtils.isPlaceholder(tag)) {
				return;
			}
			boolean isChoice = NanoMessagePsiUtils.isChoice(tag);

			for (PsiElement child : tag.getChildren()) {
				if (child instanceof XmlAttribute) {
					highlightPlaceholder(child.getFirstChild(), annotationHolder, isChoice);
					continue;
				}
				highlightPlaceholder(child, annotationHolder, isChoice);
			}
		}
	}

	private void highlightPlaceholder(PsiElement psiElement, AnnotationHolder annotationHolder, boolean choice) {
		annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
				.range(psiElement)
				.textAttributes(choice ? NanoMessageSyntaxHighlighter.CHOICE : NanoMessageSyntaxHighlighter.PLACEHOLDER)
				.create();
	}
}
