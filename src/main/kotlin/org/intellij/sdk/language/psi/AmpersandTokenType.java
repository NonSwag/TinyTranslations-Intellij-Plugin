package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.AmpersandLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class AmpersandTokenType extends IElementType {

	public AmpersandTokenType(@NonNls @NotNull String debugName) {
		super(debugName, AmpersandLanguage.INSTANCE);
	}

	@Override
	public String toString() {
		return "AmpersandTokenType." + super.toString();
	}
}
