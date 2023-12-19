package org.intellij.sdk.language;

import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.psi.TranslationsTypes;

public interface TranslationsTokenSets {

	TokenSet IDENTIFIERS = TokenSet.create(TranslationsTypes.TAG_KEY, TranslationsTypes.PH_KEY);
	TokenSet COMMENTS = TokenSet.create();
}
