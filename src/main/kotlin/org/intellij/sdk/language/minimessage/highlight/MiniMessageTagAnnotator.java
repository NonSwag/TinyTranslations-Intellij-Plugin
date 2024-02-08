package org.intellij.sdk.language.minimessage.highlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import org.intellij.sdk.language.minimessage.tag.Argument;
import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;
import org.intellij.sdk.language.minimessage.tag.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class MiniMessageTagAnnotator implements Annotator, DumbAware {

    private static final List<MiniMessageTag> TAGS = List.of(
            new ClickTag(), new ColorTag(), new FontTag(), new HoverTag(), new GradientTag(), new KeybindTag(),
            new NewlineTag(), new RainbowTag(), new ResetTag(), new ScoreTag(), new SelectorTag(), new TranslatableTag()
    );

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
        for (MiniMessageTag mmTag : TAGS) {
            if (!(element instanceof XmlTag tag)) {
                return;
            }
            if (!mmTag.check(tag.getName())) {
                return;
            }

            Argument arg = mmTag;
            int i = 0;
            outer:
            while (true) {
                XmlAttribute attr = tag.getAttributes()[i++];
                String attrText = attr.getValue();
                for (Argument child : arg.getChildren()) {
                    if (child.check(attrText)) {
                        arg = child;
                        continue outer;
                    }
                }
                if (arg.getChildren().isEmpty()) {
                    annotationHolder.newAnnotation(HighlightSeverity.WARNING, "unexpected argument for tag '" + mmTag.getName() + "'.")
                            .range(attr.getValueElement().getTextRange())
                            .create();
                    break;
                }
                annotationHolder.newAnnotation(HighlightSeverity.WARNING, "One of the following values expected: " + arg.getChildren().stream().map(Argument::getName).collect(Collectors.joining("|")))
                        .range(attr.getValueElement().getTextRange())
                        .create();
                break;
            }
        }
    }
}
