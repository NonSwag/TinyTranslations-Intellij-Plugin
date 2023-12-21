package org.intellij.sdk.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ui.JBColor;
import com.intellij.util.ProcessingContext;
import com.intellij.util.ui.UIUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.collections.map.HashedMap;
import org.intellij.sdk.language.psi.AmpersandTypes;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class AmpersandCompletionContributor extends CompletionContributor {

	public AmpersandCompletionContributor() {
		extend(CompletionType.BASIC, psiElement().afterLeaf("&"), new CompletionProvider<>() {
			@Override
			protected void addCompletions(@NotNull CompletionParameters completionParameters, @NotNull ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
				Constants.AMP_SUGGESTIONS_LIST.forEach(suggestion -> {
					completionResultSet.addElement(LookupElementBuilder
							.create(suggestion.sug())
							.withTypeText(suggestion.name())
							.withIcon(suggestion.icon()));
				});
			}
		});
	}
}
