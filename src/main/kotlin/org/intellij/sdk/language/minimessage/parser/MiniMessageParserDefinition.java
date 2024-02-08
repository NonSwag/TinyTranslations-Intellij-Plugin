package org.intellij.sdk.language.minimessage.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lang.xml.XMLParserDefinition;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.parsing.xml.XmlParser;
import com.intellij.psi.impl.source.parsing.xml.XmlParsing;
import com.intellij.psi.impl.source.xml.XmlTokenImpl;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.xml.XmlTokenType;
import org.intellij.sdk.language.minimessage.MiniMessageFile;
import org.intellij.sdk.language.minimessage.MiniMessageLanguage;
import org.intellij.sdk.language.minimessage.lexer.MiniMessageLexerAdapter;
import org.intellij.sdk.language.nanomessage.NanoMessageFile;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes;
import org.jetbrains.annotations.NotNull;

public class MiniMessageParserDefinition extends XMLParserDefinition implements ParserDefinition {

	public static final IFileElementType FILE = new IFileElementType(MiniMessageLanguage.INSTANCE);

	@Override
	public @NotNull Lexer createLexer(Project project) {
		return new MiniMessageLexerAdapter();
	}

	@Override
	public @NotNull PsiParser createParser(Project project) {
		return new MiniMessageParser();
	}

	@Override
	public @NotNull IFileElementType getFileNodeType() {
		return FILE;
	}

	@Override
	public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
		return new MiniMessageFile(viewProvider);
	}
}
