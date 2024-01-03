package org.intellij.sdk.language.nanomessage;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.Function;
import com.intellij.util.ProcessingContext;
import de.cubbossa.translations.NamedGlobalStyles;
import net.kyori.adventure.text.format.NamedTextColor;
import org.intellij.sdk.language.Constants;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.intellij.sdk.language.TinyTranslationsIcons.*;
import static org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes.*;

public class NanoMessageCompletionContributor extends CompletionContributor {

	NanoMessageCompletionContributor() {
		extend(CompletionType.BASIC, psiElement(LITERAL).afterLeaf("<", "{"), new CompletionProvider<>() {
			@Override
			protected void addCompletions(@NotNull CompletionParameters params,
										  @NotNull ProcessingContext processingContext,
										  @NotNull CompletionResultSet completionResultSet) {
				NamedTextColor.NAMES.values().forEach((el) -> {
					String name = el.toString().toLowerCase();
					completionResultSet.addElement(element(params, name, "color",
							Constants.createImageIcon(new Color(el.value()), 14, 14)));
				});
				for (NamedGlobalStyles value : NamedGlobalStyles.values()) {
					completionResultSet.addElement(element(params, value.name().toLowerCase(), "style",
							Tag));
				}

				completionResultSet.addElement(element(params, "bold", "decoration",
						Tag, e -> e.withBoldness(true)));
				completionResultSet.addElement(element(params, "underlined", "decoration",
						Tag, e -> e.withItemTextUnderlined(true)));
				completionResultSet.addElement(element(params, "italic", "decoration",
						Tag, e -> e.withItemTextItalic(true)));
				completionResultSet.addElement(element(params, "strikethrough", "decoration",
						Tag, e -> e.withStrikeoutness(true)));
				completionResultSet.addElement(element(params, "obfuscated", "decoration",
						Tag));

				completionResultSet.addElement(element(params, "darker", "modifier",
						Tag));
				completionResultSet.addElement(element(params, "brighter", "modifier",
						Tag));
			}
		});
	}

	private LookupElement element(CompletionParameters completionParameters, String name, String type, Icon icon) {
		return element(completionParameters, name, type, icon, null);
	}

	private LookupElement element(CompletionParameters completionParameters, String name, String type, Icon icon, Function<LookupElementBuilder, LookupElementBuilder> fun) {
		var el = LookupElementBuilder
				.create(name)
				.withPresentableText(name)
				.withTypeText(type)
				.withIcon(icon);
		if (fun != null) {
			el = fun.apply(el);
		}
		return el;
	}
}
