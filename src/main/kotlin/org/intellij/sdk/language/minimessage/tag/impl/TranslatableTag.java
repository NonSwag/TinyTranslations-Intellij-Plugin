package org.intellij.sdk.language.minimessage.tag.impl;

import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

public class TranslatableTag extends MiniMessageTag {

    public TranslatableTag() {
        super("lang", "translate", "tr");

        argument(keyArgument()
                .completions(s -> Constants.TRANSLATION_KEYS.getCompletions(s).stream().map(LookupElementBuilder::create).toList())
                .argument(anyFollowingArguments("<minimessage arguments>")));
    }
}
