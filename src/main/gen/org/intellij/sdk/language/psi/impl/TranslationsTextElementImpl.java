// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.TranslationsTypes.*;
import org.intellij.sdk.language.psi.*;

public class TranslationsTextElementImpl extends TranslationsElementImpl implements TranslationsTextElement {

  public TranslationsTextElementImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull TranslationsVisitor visitor) {
    visitor.visitTextElement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TranslationsVisitor) accept((TranslationsVisitor)visitor);
    else super.accept(visitor);
  }

}
