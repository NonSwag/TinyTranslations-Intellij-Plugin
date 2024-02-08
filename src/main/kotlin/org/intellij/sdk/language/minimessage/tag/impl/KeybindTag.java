package org.intellij.sdk.language.minimessage.tag.impl;

import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

public class KeybindTag extends MiniMessageTag {

    public KeybindTag() {
        super("key");

        argument(keyArgument());
    }
}
