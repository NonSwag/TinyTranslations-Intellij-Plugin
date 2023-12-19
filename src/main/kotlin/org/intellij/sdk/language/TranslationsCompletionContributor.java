package org.intellij.sdk.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.intellij.sdk.language.psi.TranslationsTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class TranslationsCompletionContributor extends CompletionContributor {

	private static final List<String> VALUES = new ArrayList<>(List.of(
			"msg:", "style:", "click:", "hover:", "repeat:", "darker", "lighter", "url"
	));

	TranslationsCompletionContributor() {
		extend(CompletionType.BASIC, psiElement(TranslationsTypes.TAG_KEY), new CompletionProvider<>() {
			@Override
			protected void addCompletions(@NotNull CompletionParameters completionParameters,
										  @NotNull ProcessingContext processingContext,
										  @NotNull CompletionResultSet completionResultSet) {

				NamedTextColor.NAMES.keys().forEach(s -> {
					completionResultSet.addElement(LookupElementBuilder
							.create(s)
							.withInsertHandler((insertionContext, lookupElement) -> {

							}));
				});
				VALUES.addAll(TextDecoration.NAMES.keys());


				VALUES.forEach(s -> completionResultSet.addElement(LookupElementBuilder.create(s)));
			}
		});
	}
}
