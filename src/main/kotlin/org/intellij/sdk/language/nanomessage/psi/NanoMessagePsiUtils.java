package org.intellij.sdk.language.nanomessage.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class NanoMessagePsiUtils {

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
