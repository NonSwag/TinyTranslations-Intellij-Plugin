package org.intellij.sdk.language.nanomessage.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class NanoMessagePsiUtils {

	public static boolean isPlaceholder(XmlTag tag) {
		return tag.getFirstChild().getText() == "{";
	}

	public static boolean isChoice(XmlTag tag) {
		return Arrays.stream(tag.getAttributes()).anyMatch(xmlAttribute -> xmlAttribute.getFirstChild().getText() == "?");
	}

	public static boolean beforeIs(@NotNull PsiElement token, IElementType... types) {
		for (IElementType type : types) {
			token = token.getPrevSibling();
			if (token == null) {
				return false;
			}
			if (!token.getNode().getElementType().equals(type)) {
				return false;
			}
		}
		return true;
	}

	public static boolean nextIs(@NotNull PsiElement token, IElementType... types) {
		for (IElementType type : types) {
			token = token.getNextSibling();
			if (token == null) {
				return false;
			}
			if (!token.getNode().getElementType().equals(type)) {
				return false;
			}
		}
		return true;
	}
}
