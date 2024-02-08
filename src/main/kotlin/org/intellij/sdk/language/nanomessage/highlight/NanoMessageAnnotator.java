package org.intellij.sdk.language.nanomessage.highlight;

import com.intellij.codeInspection.IntentionAndQuickFixAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTokenType;
import org.intellij.lang.annotations.RegExp;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.nanomessage.NanoMessageElementFactory;
import org.intellij.sdk.language.nanomessage.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NanoMessageAnnotator implements Annotator, DumbAware {

	private static final String[] ACTIONS = {
			"change_page", "copy_to_clipboard", "open_file", "open_url", "run_command", "suggest_command"
	};

	private record Rule(String key, @RegExp String[] patterns) {

	}
	private static final Rule[] RULES = {
			new Rule("click", new String[]{ String.join("|", ACTIONS) }),
			new Rule("color", new String[]{ String.join("|", Constants.COLORS.keySet()) + "|(#[0-9a-f]{6})"}),
			new Rule("repeat", new String[]{ "[0-9]+" }),
	};


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

		if (psiElement instanceof NanoMessageSelfClosingTag contentTag) {
			annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
					.range(contentTag)
					.textAttributes(NanoMessageSyntaxHighlighter.TAG)
					.create();
		}
		else if (psiElement instanceof NanoMessageOpenTag || psiElement instanceof NanoMessageCloseTag) {
			annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
					.range(psiElement)
					.textAttributes(NanoMessageSyntaxHighlighter.TAG)
					.create();
		}
		else if (psiElement instanceof NanoMessagePlaceholder contentTag) {
			annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
					.range(contentTag)
					.textAttributes(NanoMessageSyntaxHighlighter.PLACEHOLDER)
					.create();
		}
		else if (psiElement.getParent() instanceof NanoMessageChoicePlaceholder) {
			if (!(psiElement instanceof NanoMessageChoiceOption)) {
				annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
						.range(psiElement)
						.textAttributes(NanoMessageSyntaxHighlighter.CHOICE)
						.create();
			} else {
				if (psiElement.getFirstChild() instanceof NanoMessageTextElement) {
					annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
							.range(psiElement)
							.textAttributes(NanoMessageSyntaxHighlighter.ATTRIBUTE)
							.create();
				}
			}
		}
		else if (psiElement instanceof NanoMessageAttribute) {
			annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
					.range(psiElement)
					.textAttributes(NanoMessageSyntaxHighlighter.ATTRIBUTE)
					.create();
		}
		if (psiElement instanceof NanoMessageSelfClosingTag selfClosingTag) {
			annotationHolder.newAnnotation(HighlightSeverity.WARNING, "Prefer placeholder to self closing tags")
					.range(selfClosingTag)
					.withFix(new IntentionAndQuickFixAction() {
						@Override
						public @IntentionName @NotNull String getName() {
							return "Replace self-closing tag with placeholder tag";
						}

						@Override
						public @IntentionFamilyName @NotNull String getFamilyName() {
							return "NanoMessage code style";
						}

						@Override
						public void applyFix(@NotNull Project project, PsiFile psiFile, @Nullable Editor editor) {
							String t = selfClosingTag.getText();
							t = t.substring(1, t.length() - 2);
							selfClosingTag.replace(NanoMessageElementFactory.createPlaceholder(project, t));
						}
					})
					.create();
		}
		else if (psiElement instanceof NanoMessageContentTag contentTag) {
			for (Rule rule : RULES) {
				if (!contentTag.getOpenTag().getKey().getText().equalsIgnoreCase(rule.key)) {
					continue;
				}
				List<NanoMessageAttribute> attributes = contentTag.getOpenTag().getAttributes().getAttributeList();
				int max = Math.min(rule.patterns.length, attributes.size());
				for (int i = 0; i < max; i++) {
					String pattern = rule.patterns[i];
					NanoMessageAttribute attribute = attributes.get(i);
					if (!attribute.getText().matches(pattern)) {
						annotationHolder.newAnnotation(HighlightSeverity.WARNING, "Attribute must follow pattern '" + pattern + "'.")
								.range(attribute)
								.create();
					}
				}
			}
		}
	}
}
