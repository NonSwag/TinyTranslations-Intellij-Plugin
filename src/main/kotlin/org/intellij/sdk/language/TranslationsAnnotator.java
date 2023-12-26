package org.intellij.sdk.language;

import com.intellij.codeInspection.IntentionAndQuickFixAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.intellij.sdk.language.psi.TranslationsSelfClosingTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TranslationsAnnotator implements Annotator {
	@Override
	public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
		if (psiElement instanceof TranslationsContentTag contentTag) {
			annotationHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
					.range(contentTag)
					.create();
		}
		if (psiElement instanceof TranslationsSelfClosingTag selfClosingTag) {
			annotationHolder.newAnnotation(HighlightSeverity.WARNING, "Prefer placeholder to self closing tags")
					.range(selfClosingTag)
					.withFix(new IntentionAndQuickFixAction() {
						@Override
						public @IntentionName @NotNull String getName() {
							return "Replace self-closing tag with placeholder tag";
						}

						@Override
						public @IntentionFamilyName @NotNull String getFamilyName() {
							return null;
						}


						@Override
						public void applyFix(@NotNull Project project, PsiFile psiFile, @Nullable Editor editor) {
							String t = selfClosingTag.getText();
							t = t.substring(1, t.length() - 2);
							selfClosingTag.replace(TranslationsElementFactory.createPlaceholder(project, t));
						}
					})
					.create();
		}
	}
}
