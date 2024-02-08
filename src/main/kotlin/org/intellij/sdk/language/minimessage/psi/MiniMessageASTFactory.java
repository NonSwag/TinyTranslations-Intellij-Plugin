package org.intellij.sdk.language.minimessage.psi;

import com.intellij.lang.ASTFactory;
import com.intellij.lang.xml.XmlASTFactory;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LazyParseableElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.impl.source.tree.XmlFileElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILazyParseableElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.psi.xml.XmlElementType.*;

public class MiniMessageASTFactory extends XmlASTFactory {

    @Override
    public @Nullable LazyParseableElement createLazy(@NotNull ILazyParseableElementType type, CharSequence text) {
        return super.createLazy(type, text);
    }

    @Override
    public @Nullable LeafElement createLeaf(@NotNull IElementType type, @NotNull CharSequence text) {
        return super.createLeaf(type, text);
    }

    @Override
    public CompositeElement createComposite(@NotNull IElementType type) {
        if (type == XML_TAG) {
            return new MiniMessageTagImpl();
        }
        return super.createComposite(type);
    }
}
