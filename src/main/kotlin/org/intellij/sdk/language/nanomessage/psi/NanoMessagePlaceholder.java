package org.intellij.sdk.language.nanomessage.psi;

import com.intellij.psi.PsiElement;

public interface NanoMessagePlaceholder extends PsiElement {

    String getName();

    String setName(String name);

    default boolean hasAttributes() {
        return getAttributeCount() == 0;
    }

    int getAttributeCount();

    PsiElement[] getAttributes();

    PsiElement getAttribute(int index);
}
