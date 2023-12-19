package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.TranslationsLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TranslationsElementType extends IElementType {

	public TranslationsElementType(@NonNls @NotNull String debugName) {
		super(debugName, TranslationsLanguage.INSTANCE);
	}
}
