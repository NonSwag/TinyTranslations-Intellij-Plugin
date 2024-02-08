package org.intellij.sdk.language.minimessage.tag.impl;

import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.minimessage.tag.Argument;
import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

import java.util.List;

public class GradientTag extends MiniMessageTag {

    public GradientTag() {
        super("gradient", "transition");

        argument(repeatingArg());
    }

    private Argument repeatingArg() {
        return new Argument("<color...>") {
            @Override
            public boolean check(String arg) {
                return Constants.COLORS.containsKey(arg) || arg.matches("#[0-9a-f]{6}");
            }

            @Override
            public List<Argument> getChildren() {
                return List.of(
                    repeatingArg(),
                    GradientTag.intArgument(0)
                );
            }
        };
    }
}
