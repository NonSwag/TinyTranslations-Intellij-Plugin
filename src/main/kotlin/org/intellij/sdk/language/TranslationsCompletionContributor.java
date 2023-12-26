package org.intellij.sdk.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.ide.DataManager;
import com.intellij.ide.startup.importSettings.StartupImportIcons;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.ui.JBColor;
import com.intellij.util.Consumer;
import com.intellij.util.Function;
import com.intellij.util.ProcessingContext;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.intellij.sdk.language.psi.TranslationsTypes;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class TranslationsCompletionContributor extends CompletionContributor {

	TranslationsCompletionContributor() {
		extend(CompletionType.BASIC, psiElement(TranslationsTypes.TAG_KEY), new CompletionProvider<>() {
			@Override
			protected void addCompletions(@NotNull CompletionParameters params,
										  @NotNull ProcessingContext processingContext,
										  @NotNull CompletionResultSet completionResultSet) {
				NamedTextColor.NAMES.values().forEach((el) -> {
					String name = el.toString().toLowerCase();
					completionResultSet.addElement(element(params, name, "color",
							Constants.createImageIcon(new Color(el.value()), 14, 14)));
				});
				completionResultSet.addElement(element(params, "bold", "decoration",
						AllIcons.FileTypes.Xml, e -> e.withBoldness(true)));
				completionResultSet.addElement(element(params, "underlined", "decoration",
						AllIcons.FileTypes.Xml, e -> e.withItemTextUnderlined(true)));
				completionResultSet.addElement(element(params, "italic", "decoration",
						AllIcons.FileTypes.Xml, e -> e.withItemTextItalic(true)));
				completionResultSet.addElement(element(params, "strikethrough", "decoration",
						AllIcons.FileTypes.Xml, e -> e.withStrikeoutness(true)));
				completionResultSet.addElement(element(params, "obfuscated", "decoration",
						AllIcons.FileTypes.Xml));

				completionResultSet.addElement(element(params, "darker", "modifier",
						AllIcons.FileTypes.Xml));
			}
		});
	}

	private LookupElement element(CompletionParameters completionParameters, String name, String type, Icon icon) {
		return element(completionParameters, name, type, icon, null);
	}

	private LookupElement element(CompletionParameters completionParameters, String name, String type, Icon icon, Function<LookupElementBuilder, LookupElementBuilder> fun) {
		var el = LookupElementBuilder
				.create(name + "></" + name + ">")
				.withPresentableText(name)
				.withTypeText(type)
				.withIcon(icon)
				.withInsertHandler((insertionContext, lookupElement) -> {
					CaretModel model = completionParameters.getEditor().getCaretModel();
					model.moveToOffset(model.getOffset() - name.length() - 3);
				});
		if (fun != null) {
			el = fun.apply(el);
		}
		return el;
	}
}
