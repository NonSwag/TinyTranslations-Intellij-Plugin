package org.intellij.sdk.language.nanomessage.highlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTokenType;
import org.intellij.sdk.language.nanomessage.psi.NanoMessagePlaceholder;
import org.jetbrains.annotations.NotNull;

public class NanoMessageAnnotator implements Annotator, DumbAware {

	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		if (psiElement.getNode().getElementType().equals(XmlElementType.XML_TAG)) {
			int len = psiElement.getChildren().length;
			int i = 0;
			var el = psiElement.getChildren()[0];
			while (i < len-1 && el.getNode().getElementType() != XmlTokenType.XML_TAG_END
					&& el.getNode().getElementType() != XmlTokenType.XML_EMPTY_ELEMENT_END)
			{
				el = psiElement.getChildren()[++i];
				annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
						.range(el)
						.textAttributes(NanoMessageSyntaxHighlighter.TAG)
						.create();
			}

			el = psiElement.getLastChild();
			if (el.getNode().getElementType() != XmlTokenType.XML_EMPTY_ELEMENT_END && el.getNode().getElementType() != XmlTokenType.XML_TAG_END) {
				return;
			}
			int t = len;
			len = i;
			i = t - 1;
			el = psiElement.getChildren()[i];
			while (i > len && el.getNode().getElementType() != XmlTokenType.XML_END_TAG_START) {
				annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
						.range(el)
						.textAttributes(NanoMessageSyntaxHighlighter.TAG)
						.create();
				el = psiElement.getChildren()[--i];
			}
		}
		if (psiElement instanceof NanoMessagePlaceholder contentTag) {
			annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
					.range(contentTag)
					.textAttributes(NanoMessageSyntaxHighlighter.PLACEHOLDER)
					.create();
		}
//		else if (psiElement.getParent() instanceof NanoMessageChoicePlaceholder) {
//			if (!(psiElement instanceof NanoMessageChoiceOption)) {
//				annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//						.range(psiElement)
//						.textAttributes(NanoMessageSyntaxHighlighter.CHOICE)
//						.create();
//			} else {
//				if (psiElement.getFirstChild() instanceof NanoMessageTextElement) {
//					annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//							.range(psiElement)
//							.textAttributes(NanoMessageSyntaxHighlighter.ATTRIBUTE)
//							.create();
//				}
//			}
//		}
	}
}
