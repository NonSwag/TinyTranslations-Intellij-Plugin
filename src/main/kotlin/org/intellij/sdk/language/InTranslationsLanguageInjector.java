package org.intellij.sdk.language;

import com.intellij.json.JsonLanguage;
import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.lang.injection.general.SimpleInjection;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.intellij.sdk.language.psi.TranslationsContents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InTranslationsLanguageInjector implements LanguageInjectionContributor {
  @Override
  public @Nullable Injection getInjection(@NotNull PsiElement context) {
    PsiElement element = getContentTagOpen(context);
    if (element == null) return null;

    if (element.getText().contains("nbt")) {
      return new SimpleInjection(
          JsonLanguage.INSTANCE,
          "", "", null
      );
    } else if (element.getText().contains("legacy")) {
      return new SimpleInjection(
          AmpersandLanguage.INSTANCE,
          "", "", null
      );
    }
    return null;
  }

  private @Nullable PsiElement getContentTagOpen(PsiElement context) {
    if (context instanceof TranslationsContents contents) {
      if (contents.getParent() instanceof TranslationsContentTag contentTag) {
        return contentTag.getOpenTag();
      }
    }
    return null;
  }
}
