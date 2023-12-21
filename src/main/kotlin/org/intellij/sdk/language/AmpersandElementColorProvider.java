package org.intellij.sdk.language;

import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.AmpersandElementType;
import org.intellij.sdk.language.psi.AmpersandFormatter;
import org.intellij.sdk.language.psi.AmpersandHexcolorFormat;
import org.intellij.sdk.language.psi.AmpersandTypes;
import org.intellij.sdk.language.psi.impl.AmpersandHexcolorFormatImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class AmpersandElementColorProvider implements ElementColorProvider {

  @Override
  public @Nullable Color getColorFrom(@NotNull PsiElement psiElement) {
    if (psiElement instanceof AmpersandFormatter formatter) {
      if (formatter.getFormat().getHexcolorFormat() != null) {
        return new Color(Integer.parseInt(formatter.getFormat().getHexcolorFormat().getText().substring(1), 16));
      }
    }
    return null;
  }

  @Override
  public void setColorTo(@NotNull PsiElement psiElement, @NotNull Color color) {
    if (psiElement instanceof AmpersandHexcolorFormat hex) {
     // hex.replace(AmpersandTypes.Factory.createElement());
    }
  }
}
