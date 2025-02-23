// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.legacy.common.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LegacyVisitor extends PsiElementVisitor {

  public void visitContent(@NotNull LegacyContent o) {
    visitPsiElement(o);
  }

  public void visitFormatter(@NotNull LegacyFormatter o) {
    visitFormatting(o);
  }

  public void visitString(@NotNull LegacyString o) {
    visitPsiElement(o);
  }

  public void visitFormatting(@NotNull LegacyFormatting o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
