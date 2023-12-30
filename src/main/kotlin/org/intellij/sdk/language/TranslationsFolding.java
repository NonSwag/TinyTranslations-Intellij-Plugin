package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import com.intellij.psi.impl.source.tree.RecursiveTreeElementWalkingVisitor;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TranslationsFolding extends FoldingBuilderEx implements DumbAware {

	@Override
	public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement psiElement, @NotNull Document document, boolean b) {
		List<FoldingDescriptor> descriptors = new ArrayList<>();

		psiElement.accept(new PsiRecursiveElementWalkingVisitor() {
			@Override
			public void visitElement(@NotNull PsiElement element) {
				super.visitElement(element);
				if (element instanceof TranslationsContentTag contentTag) {
					descriptors.add(new FoldingDescriptor(contentTag.getNode(), contentTag.getTextRange()));
				}
			}
		});
		return descriptors.toArray(FoldingDescriptor[]::new);
	}

	@Override
	public @Nullable String getPlaceholderText(@NotNull ASTNode astNode) {
		if (astNode.getPsi() instanceof TranslationsContentTag contentTag) {
			return "<" + contentTag.getOpenTag().getKey().getText() + ">" + StringUtil.THREE_DOTS + "</>";
		}
		return StringUtil.THREE_DOTS;
	}

	@Override
	public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
		return false;
	}
}
