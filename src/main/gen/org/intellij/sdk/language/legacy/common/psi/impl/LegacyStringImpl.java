// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.legacy.common.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.legacy.common.psi.LegacyTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.intellij.sdk.language.legacy.common.psi.*;

public class LegacyStringImpl extends ASTWrapperPsiElement implements LegacyString {

  public LegacyStringImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LegacyVisitor visitor) {
    visitor.visitString(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LegacyVisitor) accept((LegacyVisitor)visitor);
    else super.accept(visitor);
  }

}
