// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.sdk.language.psi.impl.*;

public interface AmpersandTypes {

  IElementType CONTENT = new AmpersandElementType("CONTENT");

  IElementType FORMAT = new AmpersandTokenType("FORMAT");
  IElementType MISC = new AmpersandTokenType("MISC");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CONTENT) {
        return new AmpersandContentImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
