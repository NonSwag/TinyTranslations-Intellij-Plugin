package org.intellij.sdk.language.minimessage.editor;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.XmlRecursiveElementWalkingVisitor;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.minimessage.tag.impl.KeybindTag;
import org.intellij.sdk.language.minimessage.tag.impl.TranslatableTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MiniMessageTagFolding extends FoldingBuilderEx implements DumbAware {

    private final TranslatableTag translatableTag = new TranslatableTag();
    private final KeybindTag keybindTag = new KeybindTag();

    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean b) {
        // Initialize the group of folding regions that will expand/collapse together.
        // Initialize the list of folding regions
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        root.accept(new XmlRecursiveElementWalkingVisitor() {

            @Override
            public void visitXmlTag(@NotNull XmlTag tag) {
                super.visitXmlTag(tag);
                TextRange tr = tag.getTextRange();
                FoldingGroup group = FoldingGroup.newGroup("minimessagefolding");

                if (keybindTag.check(tag.getName()) || translatableTag.check(tag.getName())) {
                    descriptors.add(new FoldingDescriptor(tag.getNode(), tag.getTextRange(), group, Collections.singleton(tag)));

                } else if (!tag.getValue().getTextRange().isEmpty()) {
                    descriptors.add(new FoldingDescriptor(tag.getNode(),
                            new TextRange(tr.getStartOffset() + tag.getName().length() + 1, tr.getEndOffset() - 1),
                            group, Collections.singleton(tag)));
                }
            }
        });
        return descriptors.toArray(FoldingDescriptor.EMPTY);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode astNode) {
        if (astNode.getPsi() instanceof XmlTag tag) {
            if (keybindTag.check(tag.getName())) {
                String key = tag.getAttributes()[0].getValue();
                return Constants.CONTROL_KEY_NAMES.getProperty(key);
            } else if (translatableTag.check(tag.getName())) {
                String key = tag.getAttributes()[0].getValue();
                if (key == null) {
                    return null;
                }
                String translation = (String) Constants.TRANSLATION_KEY_VALUES.get(key);
                if (translation == null) {
                    return null;
                }
                translation = String.format(translation, Arrays.stream(Arrays.copyOfRange(tag.getAttributes(), 1, tag.getAttributes().length)).map(XmlAttribute::getValue).toArray());
                if (translation.length() > 50) {
                    translation = translation.substring(0, 50);
                }
                return translation;
            } else {
                return "...";
            }
        }
        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return astNode.getPsi() instanceof XmlTag tag && (keybindTag.check(tag.getName()) || translatableTag.check(tag.getName()));
    }
}
