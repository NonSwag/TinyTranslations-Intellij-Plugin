// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class TranslationsVisitor extends PsiElementVisitor {

  public void visitContent(@NotNull TranslationsContent o) {
    visitPsiElement(o);
  }

  public void visitContentTag(@NotNull TranslationsContentTag o) {
    visitElement(o);
  }

  public void visitElement(@NotNull TranslationsElement o) {
    visitPsiElement(o);
  }

  public void visitPlaceholder(@NotNull TranslationsPlaceholder o) {
    visitElement(o);
  }

  public void visitSelfClosingTag(@NotNull TranslationsSelfClosingTag o) {
    visitElement(o);
  }

  public void visitTextElement(@NotNull TranslationsTextElement o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
