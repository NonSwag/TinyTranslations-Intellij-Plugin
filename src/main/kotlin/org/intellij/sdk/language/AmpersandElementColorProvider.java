package org.intellij.sdk.language;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.XmlElementFactory;
import com.intellij.psi.XmlElementFactoryImpl;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import net.kyori.adventure.text.format.NamedTextColor;
import org.intellij.sdk.language.psi.*;
import org.intellij.sdk.language.psi.impl.AmpersandHexcolorFormatImpl;
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
          return new Color(Constants.COLOR_ENCODINGS.getKeysByValue(color.getColorFormat().getText()).get(0).value());
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