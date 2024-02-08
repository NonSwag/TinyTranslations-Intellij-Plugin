package org.intellij.sdk.language.nanomessage;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContentTag;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageContents;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTextElement;

public class NanoMessageElementFactory {

	public static NanoMessageFile createFile(Project project, String text) {
		String name = "dummy.lang";
		return (NanoMessageFile) PsiFileFactory.getInstance(project).createFileFromText(name, NanoMessageLanguage.INSTANCE, text);
	}

	public static PsiElement createPlaceholder(Project project, String key) {
		final NanoMessageFile file = createFile(project, "{" + key + "}");

		// file -> contents -> placeholder
		return file.getFirstChild();
	}

	public static PsiElement createKey(Project project, String key) {
		final NanoMessageFile file = createFile(project, "<" + key + "/>");
		// file -> contents -> self_closing -> key
		return file.getFirstChild().getFirstChild().getFirstChild();
	}

	public static PsiElement createContentTagKey(Project project, String key, NanoMessageContents contents) {
		final NanoMessageFile file = createFile(project, "<" + key + ">#</" + key + ">");

		// contents -> content_tag
		var target = file.getFirstChild();
		target.getChildren()[1].replace(contents);
		return target;
	}

	public static NanoMessageTextElement createText(Project project, String text) {
		final NanoMessageFile file = createFile(project, "<pre>" + text + "</pre>");
		if (file.getFirstChild().getFirstChild() instanceof NanoMessageContentTag contentTag) {
			return (NanoMessageTextElement) contentTag.getContents().getFirstChild();
		}
		return null;
	}
}
