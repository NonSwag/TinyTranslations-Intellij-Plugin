package org.intellij.sdk.language;

import com.intellij.codeInsight.template.TemplateActionContext;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;

public class TranslationsClickTemplateContextType extends TemplateContextType {

	TranslationsClickTemplateContextType() {
		super("Translations");
	}

	@Override
	public boolean isInContext(@NotNull TemplateActionContext templateActionContext) {
		return true;
	}
}
