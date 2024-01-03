package org.intellij.sdk.language.nanomessage;

import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.lang.injection.general.Injection;
import com.intellij.lang.injection.general.LanguageInjectionContributor;
import com.intellij.lang.injection.general.SimpleInjection;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageOpenTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTextElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.yaml.YAMLElementType;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static org.jetbrains.yaml.YAMLElementTypes.*;

public class YmlLanguageInjector implements LanguageInjectionContributor, DumbAware {

  public YmlLanguageInjector() {}

  private static final YAMLElementType[] INJECT_TYPES = {
          SCALAR_PLAIN_VALUE, SCALAR_TEXT_VALUE, SCALAR_QUOTED_STRING
  };

  @Override
  public @Nullable Injection getInjection(@NotNull PsiElement psiElement) {
    String path = psiElement.getContainingFile().getOriginalFile().getVirtualFile().getPath();
    if (!path.contains("/lang/")) {
      return null;
    }
    for (YAMLElementType injectType : INJECT_TYPES) {
      if (psiElement.getNode().getElementType().equals(injectType)) {
        return new SimpleInjection(NanoMessageLanguage.INSTANCE, "", "", null);
      }
    }
    return null;
  }

  private @Nullable NanoMessageOpenTag getContentTagOpen(PsiElement context) {
    if (context instanceof NanoMessageTextElement contents) {
      if (contents.getParent() instanceof NanoMessageContentTag contentTag) {
        return contentTag.getOpenTag();
      }
    }
    return null;
  }

  public void getLanguagesToInject(@NotNull MultiHostRegistrar multiHostRegistrar, @NotNull PsiElement psiElement) {
    NanoMessageOpenTag open = getContentTagOpen(psiElement);
//    if (open == null) {
//      return;
//    }
//    String s = open.getText();
//    s = s.substring(1, s.length() - 1).toLowerCase();
//    Language language = switch (s) {
//      case "nbt" -> JsonLanguage.INSTANCE;
//      case "legacy:'&'" -> AmpersandLanguage.INSTANCE;
//      default -> null;
//    };
//    if (language == null) {
//      return;
//    }
//    multiHostRegistrar.startInjecting(language)
//            .addPlace("", "", (PsiLanguageInjectionHost) psiElement, psiElement.getTextRange())
//            .doneInjecting();
  }
}
