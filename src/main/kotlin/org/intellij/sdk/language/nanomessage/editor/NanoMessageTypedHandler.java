package org.intellij.sdk.language.nanomessage.editor;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.nanomessage.NanoMessageFile;
import org.intellij.sdk.language.nanomessage.NanoMessageFileType;
import org.intellij.sdk.language.nanomessage.psi.*;
import org.jetbrains.annotations.NotNull;

import static org.intellij.sdk.language.nanomessage.NanoMessagePsiUtils.beforeIs;
import static org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes.*;

public class NanoMessageTypedHandler extends TypedHandlerDelegate {

	@NotNull
	@Override
	public Result beforeCharTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file, @NotNull FileType fileType) {
		if (!(fileType instanceof NanoMessageFileType)) {
			return Result.CONTINUE;
		}
		return super.beforeCharTyped(c, project, editor, file, fileType);
	}

	@NotNull
	@Override
	public Result checkAutoPopup(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
		if (!(file instanceof NanoMessageFile)) {
			return Result.CONTINUE;
		}
		if (c == '<' || c == '{') {
			AutoPopupController.getInstance(project).autoPopupMemberLookup(editor, psiFile -> true);
		}
		return super.checkAutoPopup(c, project, editor, file);
	}



	@NotNull
	@Override
	public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
		if (!(file instanceof NanoMessageFile)) {
			return Result.CONTINUE;
		}
		int offset = editor.getCaretModel().getOffset();
		PsiElement at = file.findElementAt(offset - 2);

		if (at != null && at.getParent() instanceof NanoMessageKey key) {
			if (key.getParent() instanceof NanoMessageOpenTag openTag) {
				if (openTag.getParent() instanceof NanoMessageContentTag contentTag) {
					if (contentTag.getCloseTag() != null) {
						NanoMessageCloseTag closeTag = contentTag.getCloseTag();

						int delta = offset - openTag.getTextOffset();
						editor.getDocument().insertString(closeTag.getTextOffset() + delta + 1, c + "");
						return Result.CONTINUE;
					}
				}
			} else if (key.getParent() instanceof NanoMessageCloseTag closeTag) {
				if (closeTag.getParent() instanceof NanoMessageContentTag contentTag) {
					NanoMessageOpenTag openTag = contentTag.getOpenTag();

					int delta = offset - closeTag.getTextOffset();
					editor.getDocument().insertString(openTag.getTextOffset() + delta - 2, c + "");
					return Result.CONTINUE;
				}
			}
		}

		if (c == '{') {
			editor.getDocument().insertString(offset, "}");
		} else if (c == '>') {
			if (at != null && at.getNode().getElementType().equals(LITERAL)) {
				if (beforeIs(at, TAG_END)) {
					return Result.CONTINUE;
				}

				PsiElement el = at;
				while (el != null && !el.getText().equals("<")) {
					el = el.getPrevSibling();
				}
				if (el != null) {
					editor.getDocument().insertString(offset, "</" + el.getNextSibling().getText() + ">");
				}
			}
		}
		else if (c == '/' && at != null) {
			if (at.getText().equals("<")) {
				if (at.getParent() instanceof NanoMessageContents contents) {
					if (contents.getParent() instanceof NanoMessageContentTag contentTag) {
						if (contentTag.getCloseTag() == null) {
							String open = contentTag.getOpenTag().getKey().getText();
							editor.getDocument().insertString(offset, open + ">");
							editor.getCaretModel().moveToOffset(offset + open.length() + 1);
						}
					}
				}
			} else {
				PsiElement el = at;
				while (el != null && !el.getText().equals("<")) {
					el = el.getPrevSibling();
				}
				if (el != null) {
					editor.getDocument().insertString(offset, ">");
					editor.getCaretModel().moveToOffset(offset + 1);
				}
			}
		}
		return super.charTyped(c, project, editor, file);
	}
}
