package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.TranslationsLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TranslationsTokenType extends IElementType {

	public TranslationsTokenType(@NonNls @NotNull String debugName) {
		super(debugName, TranslationsLanguage.INSTANCE);
	}

	@Override
	public String toString() {
		return "TranslationsTokenType." + super.toString();
	}
}
