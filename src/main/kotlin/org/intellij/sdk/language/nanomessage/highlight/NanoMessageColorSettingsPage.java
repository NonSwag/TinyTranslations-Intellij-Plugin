package org.intellij.sdk.language.nanomessage.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.intellij.sdk.language.TinyTranslationsIcons;
import org.intellij.sdk.language.nanomessage.highlight.NanoMessageSyntaxHighlighter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

import static org.intellij.sdk.language.nanomessage.highlight.NanoMessageSyntaxHighlighter.*;

public class NanoMessageColorSettingsPage implements ColorSettingsPage {

	private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
			new AttributesDescriptor("Tag", TAG),
			new AttributesDescriptor("Choice", CHOICE),
			new AttributesDescriptor("Placeholder", PLACEHOLDER),
			new AttributesDescriptor("Attribute", ATTRIBUTE),
	};

	@Override
	public @Nullable Icon getIcon() {
		return TinyTranslationsIcons.Logo;
	}

	@Override
	public @NotNull SyntaxHighlighter getHighlighter() {
		return new NanoMessageSyntaxHighlighter();
	}

	@Override
	public @NonNls @NotNull String getDemoText() {
		return """
				<tag><content_tag:<attr>123</attr>></tag>with some content<tag></content_tag></tag>
				<ph>{placeholder:<attr>'with params{}'</attr>:<attr>true</attr>:<attr>#123</attr>}</ph>
				<choice>{some_choice:on:remaining:time ? <attr>"minute"</attr> : <attr>"minutes"</attr>}</choice>
				<tag><self_closing_tag/></tag>""";
	}

	@Override
	public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return Map.of(
				"attr", ATTRIBUTE,
				"choice", CHOICE,
				"ph", PLACEHOLDER,
				"tag", TAG
		);
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
		return "NanoMessage";
	}
}
