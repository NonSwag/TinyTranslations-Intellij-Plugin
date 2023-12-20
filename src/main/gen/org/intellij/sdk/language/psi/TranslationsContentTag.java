// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TranslationsContentTag extends TranslationsElement {

  @NotNull
  TranslationsCloseTag getCloseTag();

  @NotNull
  List<TranslationsElement> getElementList();

  @NotNull
  TranslationsOpenTag getOpenTag();

}
