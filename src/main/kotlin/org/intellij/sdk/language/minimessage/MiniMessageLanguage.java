package org.intellij.sdk.language.minimessage;

import com.intellij.lang.Language;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.lang.xml.XMLLanguage;

public class MiniMessageLanguage extends Language {

    public static final MiniMessageLanguage INSTANCE = new MiniMessageLanguage();

    private MiniMessageLanguage() {
        super(XMLLanguage.INSTANCE, "MiniMessage");
    }

}
