package org.intellij.sdk.language.legacy.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.legacy.AmpersandLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AmpersandElementType extends IElementType {

	public AmpersandElementType(@NonNls @NotNull String debugName) {
		super(debugName, AmpersandLanguage.INSTANCE);
	}
}
