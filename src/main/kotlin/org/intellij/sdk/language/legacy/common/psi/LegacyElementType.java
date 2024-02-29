package org.intellij.sdk.language.legacy.common.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.legacy.ampersand.AmpersandLanguage;
import org.intellij.sdk.language.legacy.common.LegacyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LegacyElementType extends IElementType {

	public LegacyElementType(@NonNls @NotNull String debugName) {
		super(debugName, LegacyLanguage.INSTANCE);
	}
}
