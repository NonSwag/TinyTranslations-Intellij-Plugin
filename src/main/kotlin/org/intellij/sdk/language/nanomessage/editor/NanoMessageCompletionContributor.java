package org.intellij.sdk.language.nanomessage.editor;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.psi.xml.*;
import com.intellij.util.ProcessingContext;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.TinyTranslationsIcons;
import org.intellij.sdk.language.nanomessage.psi.LocalStylesFile;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class NanoMessageCompletionContributor extends CompletionContributor {

    public NanoMessageCompletionContributor() {
        extend(CompletionType.BASIC, psiElement(XmlTokenType.XML_NAME).afterLeaf("<", "{"), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters params,
                                          @NotNull ProcessingContext processingContext,
                                          @NotNull CompletionResultSet completionResultSet) {

                PropertiesFile file = LocalStylesFile.getFile(params.getEditor().getProject());
                if (file != null) {
                    for (IProperty property : file.getProperties()) {
                        completionResultSet.addElement(LookupElementBuilder
                                .create(property.getName())
                                .withTypeText("app style")
                                .withIcon(TinyTranslationsIcons.Tag));
                    }
                }

                Constants.GLOBAL_STYLES.forEach((k, v) -> completionResultSet.addElement(LookupElementBuilder
                        .create(k)
                        .withTypeText("global style")
                        .withIcon(TinyTranslationsIcons.Tag)));
            }
        });
    }

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        if (parameters.getPosition().getNode().getElementType() == XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN) {
            XmlAttributeValue val = (XmlAttributeValue) parameters.getPosition().getParent();
            parameters = parameters.withPosition(val, val.getTextOffset());
        }
        super.fillCompletionVariants(parameters, result);
    }
}
