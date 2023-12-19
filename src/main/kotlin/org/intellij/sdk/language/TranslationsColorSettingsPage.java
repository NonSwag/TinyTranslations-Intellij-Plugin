package org.intellij.sdk.language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class TranslationsColorSettingsPage implements ColorSettingsPage {

	private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
			new AttributesDescriptor("Tag", TranslationsSyntaxHighlighter.KEY),
			new AttributesDescriptor("Separator", TranslationsSyntaxHighlighter.SEPARATOR),
			new AttributesDescriptor("Attribute", TranslationsSyntaxHighlighter.ATTRIBUTE),
			new AttributesDescriptor("Bad value", TranslationsSyntaxHighlighter.BAD_CHARACTER),
			new AttributesDescriptor("Placeholder", TranslationsSyntaxHighlighter.PLACEHOLDER)
	};

	@Override
	public @Nullable Icon getIcon() {
		return null;
	}

	@Override
	public @NotNull SyntaxHighlighter getHighlighter() {
		return new TranslationsSyntaxHighlighter();
	}

	@Override
	public @NonNls @NotNull String getDemoText() {
		return """
				<gradient:black:dark_gray:black>------------ <c_primary>Pathfinder</c_primary> ------------</gradient>
				<text>Require help? Checkout the <text_hl><u><click:open_url:"https://docs.leonardbausenwein.de/getting_started/introduction.html">WIKI</click></u></text_hl>.
				
				<white>Commands:</white>
				<bg>» </bg><text><text_hl>/pf editmode</text_hl> - Create, edit and delete waypoints via GUI
				<bg>» </bg><text><text_hl>/pf node</text_hl> - Create, edit and delete waypoints via Commands
				<bg>» </bg><text><text_hl>/pf group</text_hl> - Add behaviour to multiple waypoints
				<bg>» </bg><text><text_hl>/pf visualizer</text_hl> - Compass, particles, placeholders and more
				<bg>» </bg><text><text_hl>/find</text_hl> - Find the shortest way to a nodegroup
				--- {page:'0'}/{pages:'0'} ---""";
	}

	@Override
	public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return null;
	}

	@Override
	public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
		return DESCRIPTORS;
	}

	@Override
	public ColorDescriptor @NotNull [] getColorDescriptors() {
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@Override
	public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
		return "Translations";
	}
}
