package org.intellij.sdk.language.minimessage.tag.impl;

import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

public class DecorationTag extends MiniMessageTag {

    public DecorationTag() {
        super("bold", "b", "italic", "em", "i", "underlined", "u", "strikethrough", "st", "obfuscated", "obf");

        argument(boolArgument().optional());
    }
}
