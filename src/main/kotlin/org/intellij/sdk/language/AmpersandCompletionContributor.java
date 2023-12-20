package org.intellij.sdk.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ui.JBColor;
import com.intellij.util.ProcessingContext;
import com.intellij.util.ui.UIUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.collections.map.HashedMap;
import org.intellij.sdk.language.psi.AmpersandTypes;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class AmpersandCompletionContributor extends CompletionContributor {

	private static final Map<NamedTextColor, String> COLOR_ENCODINGS = new HashMap<>();
	static {
		COLOR_ENCODINGS.put(NamedTextColor.BLACK, "0");
		COLOR_ENCODINGS.put(NamedTextColor.DARK_BLUE, "1");
		COLOR_ENCODINGS.put(NamedTextColor.DARK_GREEN, "2");
		COLOR_ENCODINGS.put(NamedTextColor.DARK_AQUA, "3");
		COLOR_ENCODINGS.put(NamedTextColor.DARK_RED, "4");
		COLOR_ENCODINGS.put(NamedTextColor.DARK_PURPLE, "5");
		COLOR_ENCODINGS.put(NamedTextColor.GOLD, "6");
		COLOR_ENCODINGS.put(NamedTextColor.GRAY, "7");
		COLOR_ENCODINGS.put(NamedTextColor.DARK_GRAY, "8");
		COLOR_ENCODINGS.put(NamedTextColor.BLUE, "9");
		COLOR_ENCODINGS.put(NamedTextColor.GREEN, "a");
		COLOR_ENCODINGS.put(NamedTextColor.AQUA, "b");
		COLOR_ENCODINGS.put(NamedTextColor.RED, "c");
		COLOR_ENCODINGS.put(NamedTextColor.LIGHT_PURPLE, "d");
		COLOR_ENCODINGS.put(NamedTextColor.YELLOW, "e");
		COLOR_ENCODINGS.put(NamedTextColor.WHITE, "f");
	}

	private record Suggestion(Icon icon, String sug, String name) {}

	private static Suggestion color(NamedTextColor col, char symbol) {
		return new Suggestion(createImageIcon(new Color(col.value()), 16, 16), "&" + symbol, col.toString().toLowerCase());
	}

	private static List<Suggestion> suggestionList = new ArrayList<>(List.of(
			new Suggestion(null, "&m", "strikethrough")
	));
	static {
		COLOR_ENCODINGS.forEach((k, v) -> suggestionList.add(color(k, v.toCharArray()[0])));
	}


	public AmpersandCompletionContributor() {
		extend(CompletionType.BASIC, psiElement().afterLeaf("&"), new CompletionProvider<>() {
			@Override
			protected void addCompletions(@NotNull CompletionParameters completionParameters, @NotNull ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
				suggestionList.forEach(suggestion -> {
					completionResultSet.addElement(LookupElementBuilder
							.create(suggestion.sug)
							.withTypeText(suggestion.name)
							.withIcon(suggestion.icon));
				});
			}
		});
	}

	public static ImageIcon createImageIcon(Color color, int width, int height) {
		BufferedImage image = UIUtil.createImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(color);
		graphics.fillRect (0, 0, width, height);
		return new ImageIcon(image);
	}
}
