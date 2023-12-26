package org.intellij.sdk.language;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.intellij.sdk.language.psi.AmpersandColorFormat;
import org.intellij.sdk.language.psi.AmpersandFormatter;

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
		return createColorFormatter(project, NamedTextColor.nearestTo(TextColor.color(color.getRGB())));
	}

	public static AmpersandFormatter createColorFormatter(Project project, NamedTextColor textColor) {
		final AmpersandFile file = createFile(project, "&" + Constants.COLOR_ENCODINGS.get(textColor));
		return (AmpersandFormatter) file.getFirstChild();
	}

}
