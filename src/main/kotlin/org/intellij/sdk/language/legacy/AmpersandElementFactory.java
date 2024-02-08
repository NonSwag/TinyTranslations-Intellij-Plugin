package org.intellij.sdk.language.legacy;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import org.intellij.sdk.language.Constants;
import org.intellij.sdk.language.legacy.psi.AmpersandFormatter;

import java.awt.*;

public class AmpersandElementFactory {

	public static AmpersandFile createFile(Project project, String text) {
		String name = "dummy.amp";
		return (AmpersandFile) PsiFileFactory.getInstance(project).createFileFromText(name, AmpersandLanguage.INSTANCE, text);
	}

	public static AmpersandFormatter createHexColorFormatter(Project project, Color color) {
		final AmpersandFile file = createFile(project, "&#" + Integer.toHexString(color.getRGB() & 0x00ffffff));
		return (AmpersandFormatter) file.getFirstChild();
	}

	public static AmpersandFormatter createColorFormatter(Project project, Color color) {
		return createColorFormatter(project, Constants.COLORS.values().stream()
				.filter(namedColor -> namedColor.value() == color.getRGB())
				.findAny()
				.orElse(Constants.BLACK));
	}

	public static AmpersandFormatter createColorFormatter(Project project, Constants.NamedColor textColor) {
		final AmpersandFile file = createFile(project, "&" + Constants.COLORS.getKeysByValue(textColor).get(0));
		return (AmpersandFormatter) file.getFirstChild();
	}

}
