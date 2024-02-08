package org.intellij.sdk.language.minimessage.tag;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MiniMessageTag extends Argument {

    protected String[] alias;

    public MiniMessageTag(String name, String... alias) {
        super(name, new ArrayList<>());
        this.alias = alias;
    }

    @Override
    public boolean check(String arg) {
        if (arg.equals(name)) {
            return true;
        }
        for (String s : alias) {
            if (arg.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
