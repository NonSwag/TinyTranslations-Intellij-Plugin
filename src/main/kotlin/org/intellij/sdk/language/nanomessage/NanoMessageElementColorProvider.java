package org.intellij.sdk.language.nanomessage;

import com.intellij.openapi.editor.ElementColorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiElement;
import net.kyori.adventure.text.format.NamedTextColor;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class NanoMessageElementColorProvider implements ElementColorProvider, DumbAware {

	@Override
	public @Nullable Color getColorFrom(@NotNull PsiElement psiElement) {
		if (psiElement instanceof NanoMessageContentTag tag) {
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
		if (psiElement instanceof NanoMessageContentTag tag) {
			tag.replace(NanoMessageElementFactory.createContentTagKey(psiElement.getProject(), toString(color), tag.getContents()));
		}
	}

	private String toString(Color color) {
		return "#" + Integer.toHexString(color.getRGB() & 0xffffff);
	}
}
