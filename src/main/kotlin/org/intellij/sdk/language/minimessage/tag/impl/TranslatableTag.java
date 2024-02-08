package org.intellij.sdk.language.minimessage.tag.impl;

import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

public class TranslatableTag extends MiniMessageTag {

    public TranslatableTag() {
        super("lang", "translate", "tr");

        argument(keyArgument().argument(anyFollowingArguments("<minimessage arguments>")));
    }
}
