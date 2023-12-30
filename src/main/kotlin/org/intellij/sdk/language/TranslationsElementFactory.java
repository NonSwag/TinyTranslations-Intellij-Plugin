package org.intellij.sdk.language;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import org.intellij.sdk.language.psi.TranslationsContents;
import org.intellij.sdk.language.psi.TranslationsTextElement;

public class TranslationsElementFactory {

	public static TranslationsFile createFile(Project project, String text) {
		String name = "dummy.lang";
		return (TranslationsFile) PsiFileFactory.getInstance(project).createFileFromText(name, TranslationsLanguage.INSTANCE, text);
	}

	public static PsiElement createPlaceholder(Project project, String key) {
		final TranslationsFile file = createFile(project, "{" + key + "}");

		// file -> contents -> placeholder
		return file.getFirstChild();
	}

	public static PsiElement createContentTagKey(Project project, String key, TranslationsContents contents) {
		final TranslationsFile file = createFile(project, "<" + key + ">#</" + key + ">");

		// file -> contents -> content_tag
		var target = file.getFirstChild().getFirstChild();
		target.getChildren()[1].replace(contents);
		return target;
	}

	public static TranslationsTextElement createText(Project project, String text) {
		final TranslationsFile file = createFile(project, text);
		return (TranslationsTextElement) file.getFirstChild();
	}
}
