package org.intellij.sdk.language.minimessage.tag.impl;

import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

public class NegatedDecorationTag extends MiniMessageTag {

    public NegatedDecorationTag() {
        super("!bold", "!b", "!italic", "!em", "!i", "!underlined", "!u", "!strikethrough", "!st", "!obfuscated", "!obf");
    }
}
