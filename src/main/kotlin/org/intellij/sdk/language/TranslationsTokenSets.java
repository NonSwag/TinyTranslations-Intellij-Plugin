package org.intellij.sdk.language;

import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.psi.TranslationsTypes;

public interface TranslationsTokenSets {

	TokenSet IDENTIFIERS = TokenSet.create(TranslationsTypes.LITERAL);
	TokenSet STRINGS = TokenSet.create(TranslationsTypes.TEXT_ELEMENT, TranslationsTypes.ATTRIBUTE);
	TokenSet COMMENTS = TokenSet.create();
}
