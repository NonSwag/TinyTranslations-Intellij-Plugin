package org.intellij.sdk.language.minimessage.tag.impl;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;

import java.util.ArrayList;
import java.util.List;

import static org.intellij.sdk.language.TinyTranslationsIcons.Tag;

public class DecorationTag extends MiniMessageTag {

    public DecorationTag() {
        super("bold", "b", "italic", "em", "i", "underlined", "u", "strikethrough", "st", "obfuscated", "obf");

        argument(boolArgument().optional());
    }

    @Override
    public List<? extends LookupElement> getCompletions(String in) {
        List<LookupElementBuilder> r = new ArrayList<>(alias.length + 1);
        r.add(LookupElementBuilder.create(List.of("bold", "b")).withBoldness(true));
        r.add(LookupElementBuilder.create(List.of("italic", "em", "i")).withItemTextItalic(true));
        r.add(LookupElementBuilder.create(List.of("underlined", "u")).withItemTextUnderlined(true));
        r.add(LookupElementBuilder.create(List.of("strikethrough", "st")).withStrikeoutness(true));
        r.add(LookupElementBuilder.create(List.of("obfuscated", "obf")));
        return r.stream().map(b -> b.withTypeText("decoration").withIcon(Tag)).toList();
    }
}
