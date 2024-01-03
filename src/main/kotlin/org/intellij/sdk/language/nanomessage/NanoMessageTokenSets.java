package org.intellij.sdk.language.nanomessage;

import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes;

public interface NanoMessageTokenSets {

	TokenSet IDENTIFIERS = TokenSet.create(NanoMessageTypes.LITERAL);
	TokenSet STRINGS = TokenSet.create(NanoMessageTypes.TEXT_ELEMENT, NanoMessageTypes.ATTRIBUTE);
	TokenSet COMMENTS = TokenSet.create();
}
