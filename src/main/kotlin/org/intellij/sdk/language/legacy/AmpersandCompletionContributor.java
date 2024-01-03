package org.intellij.sdk.language.legacy;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.Constants;
import org.jetbrains.annotations.NotNull;

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
