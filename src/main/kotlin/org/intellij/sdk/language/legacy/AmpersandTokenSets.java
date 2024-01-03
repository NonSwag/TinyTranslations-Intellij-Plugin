package org.intellij.sdk.language.legacy;

import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.legacy.psi.AmpersandTypes;

public interface AmpersandTokenSets {

	TokenSet STRING = TokenSet.create(AmpersandTypes.MISC);
}
