// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.AmpersandTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.intellij.sdk.language.psi.*;

public class AmpersandContentImpl extends ASTWrapperPsiElement implements AmpersandContent {

  public AmpersandContentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AmpersandVisitor visitor) {
    visitor.visitContent(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AmpersandVisitor) accept((AmpersandVisitor)visitor);
    else super.accept(visitor);
  }

}
