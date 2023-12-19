package org.intellij.sdk.language;

import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.psi.AmpersandTypes;

public interface AmpersandTokenSets {

	TokenSet STRING = TokenSet.create(AmpersandTypes.MISC);
}
