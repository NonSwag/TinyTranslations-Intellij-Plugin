// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.legacy.common.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.legacy.common.psi.LegacyTypes.*;
import org.intellij.sdk.language.legacy.common.psi.FormatterStub;
import org.intellij.sdk.language.legacy.common.psi.*;

public class LegacyFormatterImpl extends FormatterStub implements LegacyFormatter {

  public LegacyFormatterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LegacyVisitor visitor) {
    visitor.visitFormatter(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LegacyVisitor) accept((LegacyVisitor)visitor);
    else super.accept(visitor);
  }

}
