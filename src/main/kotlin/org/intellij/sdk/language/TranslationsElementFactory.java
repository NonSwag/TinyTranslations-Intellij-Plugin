package org.intellij.sdk.language;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.intellij.sdk.language.psi.AmpersandFormatter;
import org.intellij.sdk.language.psi.TranslationsContents;
import org.intellij.sdk.language.psi.TranslationsTextElement;

import java.awt.*;

public class TranslationsElementFactory {

	public static TranslationsFile createFile(Project project, String text) {
		String name = "dummy.lang";
		return (TranslationsFile) PsiFileFactory.getInstance(project).createFileFromText(name, TranslationsLanguage.INSTANCE, text);
	}

	public static PsiElement createPlaceholder(Project project, String key) {
		final TranslationsFile file = createFile(project, "{" + key + "}");

		// file -> contents -> placeholder
		return file.getFirstChild().getFirstChild();
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
