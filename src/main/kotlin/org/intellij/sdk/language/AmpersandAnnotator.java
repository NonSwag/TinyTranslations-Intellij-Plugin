package org.intellij.sdk.language;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lang.annotation.ProblemGroup;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.AmpersandFormatter;
import org.intellij.sdk.language.psi.AmpersandHexcolorFormat;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class AmpersandAnnotator implements Annotator {

  public static final TextAttributesKey COLOR =
      createTextAttributesKey("AMPERSAND_FORMAT", DefaultLanguageHighlighterColors.KEYWORD);

  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    if (element instanceof AmpersandFormatter) {
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
          .range(element)
          .textAttributes(COLOR)
          .create();
    }
    if (element instanceof AmpersandFormatter a) {
      if (element.getPrevSibling() instanceof AmpersandFormatter b) {
        if (a.getFormat().getFirstChild().isEquivalentTo(b.getFormat().getFirstChild())) {
          System.out.println("true");
          holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Redundant color sign " + a.getText())
                  .range(b)
                  .tooltip("Redundant color sign, overwritten by " + a.getText())
                  .create();
        }
      }
    }
  }
}
