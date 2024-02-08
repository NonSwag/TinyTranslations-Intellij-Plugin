package org.intellij.sdk.language.minimessage.editor;

import com.intellij.ide.highlighter.XmlHighlighterFactory;
import com.intellij.lexer.XmlHighlightingLexer;
import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import com.intellij.psi.XmlElementFactory;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTokenType;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.minimessage.psi.MiniMessagePsiFactory;
import org.intellij.sdk.language.nanomessage.NanoMessageElementFactory;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class MiniMessageElementColorProvider implements ElementColorProvider, DumbAware {

	@Override
	public @Nullable Color getColorFrom(@NotNull PsiElement psiElement) {
		if (psiElement instanceof XmlTag tag) {
			Color c = getColorFrom(tag.getName());
			if (c != null) {
				return c;
			}
			if (tag.getAttributes().length > 0) {
				return getColorFrom(tag.getAttributes()[0].getValueElement().getText());
			}
		}
		return null;
	}

	private Color getColorFrom(String string) {
		Constants.NamedColor color = Constants.COLORS.get(string.toLowerCase());
		if (color != null) {
			return new Color(color.value());
		}
		if (string.matches("#[0-9a-f]{6}")) {
			return new Color(Integer.parseInt(string.substring(1), 16));
		}
		return null;
	}

	@Override
	public void setColorTo(@NotNull PsiElement psiElement, @NotNull Color color) {
        if (!(psiElement instanceof XmlTag tag)) {
            return;
        }
		if (tag.getName().equals("color") && tag.getAttributes().length >= 1) {
			tag.getAttributes()[0].setValue(toString(color));
			return;
		}
        MiniMessagePsiFactory.renameTag(tag, toString(color));
    }

	private String toString(Color color) {
		return "#" + Integer.toHexString(color.getRGB() & 0xffffff);
	}
}
