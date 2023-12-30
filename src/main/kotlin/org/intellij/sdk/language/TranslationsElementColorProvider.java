package org.intellij.sdk.language;

import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import net.kyori.adventure.text.format.NamedTextColor;
import org.intellij.sdk.language.psi.TranslationsContentTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class TranslationsElementColorProvider implements ElementColorProvider {

	@Override
	public @Nullable Color getColorFrom(@NotNull PsiElement psiElement) {
		if (psiElement instanceof TranslationsContentTag tag) {
			String tagName = tag.getOpenTag().getText();
			tagName = tagName.substring(1, tagName.length() - 1);
			NamedTextColor color = NamedTextColor.NAMES.value(tagName.toLowerCase());
			if (color != null) {
				return new Color(color.value());
			}
			if (tagName.matches("#[0-9a-f]{6}")) {
				return new Color(Integer.parseInt(tagName.substring(1), 16));
			}
		}
		return null;
	}

	@Override
	public void setColorTo(@NotNull PsiElement psiElement, @NotNull Color color) {
		if (psiElement instanceof TranslationsContentTag tag) {
			tag.replace(TranslationsElementFactory.createContentTagKey(psiElement.getProject(), toString(color), tag.getContents()));
		}
	}

	private String toString(Color color) {
		return "#" + Integer.toHexString(color.getRGB() & 0xffffff);
	}
}
