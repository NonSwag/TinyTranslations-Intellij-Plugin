package org.intellij.sdk.language.minimessage;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.xml.IXmlLeafElementType;

public interface MiniMessageTokenType {

    IElementType MM_ATTRIBUTE_SEPARATOR = new IXmlLeafElementType("MM_ATTRIBUTE_SEPARATOR");
}
