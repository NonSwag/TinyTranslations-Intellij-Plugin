package org.intellij.sdk.language.legacy;

import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.legacy.psi.AmpersandColorGroup;
import org.intellij.sdk.language.legacy.psi.AmpersandFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class AmpersandElementColorProvider implements ElementColorProvider {

  @Override
  public @Nullable Color getColorFrom(@NotNull PsiElement psiElement) {
    if (psiElement instanceof AmpersandFormatter formatter) {
      AmpersandColorGroup color = formatter.getFormat().getColorGroup();
      if (color != null) {
        if (color.getColorFormat() != null) {
          return new Color(Constants.COLORS.get(color.getColorFormat().getText()).value());
        } else if (color.getHexcolorFormat() != null) {
          return new Color(Integer.parseInt(color.getHexcolorFormat().getText().substring(1), 16));
        }
      }
    }
    return null;
  }

  @Override
  public void setColorTo(@NotNull PsiElement psiElement, @NotNull Color color) {
    if (psiElement instanceof AmpersandFormatter formatter) {
      formatter.replace(AmpersandElementFactory.createHexColorFormatter(psiElement.getProject(), color));
    }
  }
}