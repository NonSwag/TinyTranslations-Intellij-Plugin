// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.sdk.language.psi.impl.*;

public interface TranslationsTypes {

  IElementType CLOSE_TAG = new TranslationsElementType("CLOSE_TAG");
  IElementType CONTENT = new TranslationsElementType("CONTENT");
  IElementType CONTENT_TAG = new TranslationsElementType("CONTENT_TAG");
  IElementType ELEMENT = new TranslationsElementType("ELEMENT");
  IElementType OPEN_TAG = new TranslationsElementType("OPEN_TAG");
  IElementType PLACEHOLDER = new TranslationsElementType("PLACEHOLDER");
  IElementType SELF_CLOSING_TAG = new TranslationsElementType("SELF_CLOSING_TAG");
  IElementType TEXT_ELEMENT = new TranslationsElementType("TEXT_ELEMENT");

  IElementType BOOL = new TranslationsTokenType("BOOL");
  IElementType MISC = new TranslationsTokenType("MISC");
  IElementType NUMBER = new TranslationsTokenType("NUMBER");
  IElementType PH_CLOSE = new TranslationsTokenType("PH_CLOSE");
  IElementType PH_KEY = new TranslationsTokenType("PH_KEY");
  IElementType PH_OPEN = new TranslationsTokenType("PH_OPEN");
  IElementType SEPARATOR = new TranslationsTokenType("SEPARATOR");
  IElementType TAG_CLOSE = new TranslationsTokenType("TAG_CLOSE");
  IElementType TAG_END = new TranslationsTokenType("TAG_END");
  IElementType TAG_KEY = new TranslationsTokenType("TAG_KEY");
  IElementType TAG_OPEN = new TranslationsTokenType("TAG_OPEN");
  IElementType VALUE = new TranslationsTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CLOSE_TAG) {
        return new TranslationsCloseTagImpl(node);
      }
      else if (type == CONTENT) {
        return new TranslationsContentImpl(node);
      }
      else if (type == CONTENT_TAG) {
        return new TranslationsContentTagImpl(node);
      }
      else if (type == OPEN_TAG) {
        return new TranslationsOpenTagImpl(node);
      }
      else if (type == PLACEHOLDER) {
        return new TranslationsPlaceholderImpl(node);
      }
      else if (type == SELF_CLOSING_TAG) {
        return new TranslationsSelfClosingTagImpl(node);
      }
      else if (type == TEXT_ELEMENT) {
        return new TranslationsTextElementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
