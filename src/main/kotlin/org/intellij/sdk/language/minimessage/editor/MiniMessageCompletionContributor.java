package org.intellij.sdk.language.minimessage.editor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlTokenType;
import com.intellij.util.Function;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.minimessage.MiniMessageTokenType;
import org.intellij.sdk.language.minimessage.tag.Argument;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.intellij.sdk.language.TinyTranslationsIcons.Tag;

public class MiniMessageCompletionContributor extends CompletionContributor {

    MiniMessageCompletionContributor() {
        extend(CompletionType.BASIC, psiElement(XmlTokenType.XML_NAME).afterLeaf("<", "{"), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters params,
                                          @NotNull ProcessingContext processingContext,
                                          @NotNull CompletionResultSet completionResultSet) {
                Constants.COLORS.values().forEach((el) -> {
                    String name = el.toString().toLowerCase();
                    completionResultSet.addElement(element(params, name, "color",
                            Constants.createImageIcon(new Color(el.value()), 14, 14)));
                });
//				for (GlobalStyles value : GlobalStyles.values()) {
//					completionResultSet.addElement(element(params, value.name().toLowerCase(), "style",
//							Tag));
//				}
                Constants.TAGS.forEach(miniMessageTag -> {
                    boolean needsArg = !miniMessageTag.getChildren().isEmpty() && miniMessageTag.getChildren().stream()
                            .noneMatch(Argument::isOptional);
                    completionResultSet.addElement(LookupElementBuilder
                            .create(miniMessageTag.getName() + (needsArg ? ":" : ""))
                            .withPresentableText(miniMessageTag.getName()));
                });

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
            }
        });

        extend(CompletionType.BASIC, psiElement().afterLeaf("\"", "'", ":"), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters params,
                                          @NotNull ProcessingContext processingContext,
                                          @NotNull CompletionResultSet completionResultSet) {
                // TODO make suggestions for arguments where possible (string constants like click actions)
                // completionResultSet.addElement(LookupElementBuilder.create("yup"));
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
