// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.legacy.common.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.sdk.language.legacy.common.psi.impl.*;

public interface LegacyTypes {

  IElementType CONTENT = new LegacyElementType("CONTENT");
  IElementType FORMATTER = new LegacyElementType("FORMATTER");
  IElementType STRING = new LegacyElementType("STRING");

  IElementType COLOR = new LegacyTokenType("COLOR");
  IElementType DECO = new LegacyTokenType("DECO");
  IElementType ESCAPE = new LegacyTokenType("ESCAPE");
  IElementType HEXCOLOR_HASH = new LegacyTokenType("HEXCOLOR_HASH");
  IElementType HEXCOLOR_X = new LegacyTokenType("HEXCOLOR_X");
  IElementType MISC = new LegacyTokenType("MISC");
  IElementType SPECIAL = new LegacyTokenType("SPECIAL");
  IElementType SYMBOL = new LegacyTokenType("SYMBOL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CONTENT) {
        return new LegacyContentImpl(node);
      }
      else if (type == FORMATTER) {
        return new LegacyFormatterImpl(node);
      }
      else if (type == STRING) {
        return new LegacyStringImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
