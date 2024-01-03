package org.intellij.sdk.language.nanomessage.editor;

import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.sdk.language.nanomessage.NanoMessageFile;
import org.intellij.sdk.language.nanomessage.psi.*;
import org.jetbrains.annotations.NotNull;

import static org.intellij.sdk.language.nanomessage.NanoMessagePsiUtils.beforeIs;
import static org.intellij.sdk.language.nanomessage.NanoMessagePsiUtils.nextIs;
import static org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes.*;

public class NanoMessageBackspaceHandler extends BackspaceHandlerDelegate {

	@Override
	public void beforeCharDeleted(char c, @NotNull PsiFile psiFile, @NotNull Editor editor) {

	}

	@Override
	public boolean charDeleted(char c, @NotNull PsiFile file, @NotNull Editor editor) {
		if (!(file instanceof NanoMessageFile)) {
			return true;
		}
		int offset = editor.getCaretModel().getOffset();
		PsiElement at = file.findElementAt(offset);

		if (at == null) {
			return true;
		}

		if (at.getParent() instanceof NanoMessageKey key) {
			if (key.getParent() instanceof NanoMessageOpenTag openTag) {
				if (openTag.getParent() instanceof NanoMessageContentTag contentTag) {
					if (contentTag.getCloseTag() != null) {
						NanoMessageCloseTag closeTag = contentTag.getCloseTag();

						int delta = offset - openTag.getTextOffset();
						editor.getDocument().deleteString(closeTag.getTextOffset() + delta, closeTag.getTextOffset() + delta + 1);
						return false;
					}
				}
			} else if (key.getParent() instanceof NanoMessageCloseTag closeTag) {
				if (closeTag.getParent() instanceof NanoMessageContentTag contentTag) {
					NanoMessageOpenTag openTag = contentTag.getOpenTag();

					int delta = offset - closeTag.getTextOffset();
					editor.getDocument().deleteString(openTag.getTextOffset() + delta - 1, openTag.getTextOffset() + delta);
					return false;
				}
			}
		}

		if (c == '{') {
			if (nextIs(at, PH_CLOSE)) {
				editor.getDocument().deleteString(offset, offset + 1);
			}
		} else if (c == '}') {
			if (beforeIs(at, PH_OPEN)) {
				editor.getDocument().deleteString(offset - 1, offset);
			}
		}
		return false;
	}
}
