package org.intellij.sdk.language.minimessage;

import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.xml.SchemaPrefix;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.HtmlXmlExtension;
import com.intellij.xml.XmlExtension;
import org.intellij.sdk.language.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class MiniMessageXmlExtension extends HtmlXmlExtension {

    @Override
    public boolean isValidTagNameChar(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '#' || c == '_' || c == '-' || c == '.' || c == '!' || c == '?';
    }

    @Override
    public boolean isAvailable(PsiFile psiFile) {
        return psiFile.getLanguage().isKindOf(MiniMessageLanguage.INSTANCE);
    }

    @Override
    public boolean isCustomTagAllowed(XmlTag tag) {
        return true;
    }

    @Override
    public boolean shouldBeHighlightedAsTag(XmlTag tag) {
        return false;
    }

    @Override
    public boolean shouldCompleteTag(XmlTag context) {
        return true;
    }

    @Override
    public boolean isSelfClosingTagAllowed(@NotNull XmlTag tag) {
        return !Constants.COLORS.containsKey(tag.getName());
    }

    @Override
    public @NotNull List<TagInfo> getAvailableTagNames(@NotNull XmlFile xmlFile, @NotNull XmlTag xmlTag) {
        return Collections.emptyList();
    }

    @Override
    public @Nullable SchemaPrefix getPrefixDeclaration(XmlTag xmlTag, String s) {
        return null;
    }
}
