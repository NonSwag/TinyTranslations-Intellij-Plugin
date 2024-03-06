package org.intellij.sdk.language.minimessage;

import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;
import org.intellij.sdk.language.minimessage.tag.MiniMessageTag;
import org.intellij.sdk.language.minimessage.tag.impl.*;

import java.util.ArrayList;
import java.util.List;

public class MiniMessageLanguage extends Language {

    public static final MiniMessageLanguage INSTANCE = new MiniMessageLanguage();
    protected final List<MiniMessageTag> tags;

    private MiniMessageLanguage(Language parent, String name) {
        super(parent, name);

        tags = new ArrayList<>();
        tags.addAll(List.of(
                new ClickTag(), new ColorTag(), new FontTag(), new HoverTag(), new GradientTag(), new KeybindTag(),
                new NewlineTag(), new RainbowTag(), new ResetTag(), new ScoreTag(), new SelectorTag(), new TranslatableTag(),
                new DecorationTag(), new NegatedDecorationTag(), new TransitionTag()
        ));
    }

    protected MiniMessageLanguage(String name) {
        this(MiniMessageLanguage.INSTANCE, name);
    }

    private MiniMessageLanguage() {
        this(XMLLanguage.INSTANCE, "MiniMessage");
    }

    public List<MiniMessageTag> getTags() {
        return new ArrayList<>(tags);
    }
}
