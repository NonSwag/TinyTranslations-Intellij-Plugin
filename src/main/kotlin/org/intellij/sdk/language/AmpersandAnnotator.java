package org.intellij.sdk.language;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.AmpersandFormatter;
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
  }
}
