package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.parser.TranslationsParser;
import org.intellij.sdk.language.psi.TranslationsTypes;
import org.jetbrains.annotations.NotNull;

public class TranslationsParserDefinition implements ParserDefinition {

	public static final IFileElementType FILE = new IFileElementType(TranslationsLanguage.INSTANCE);

	@Override
	public @NotNull Lexer createLexer(Project project) {
		return new TranslationsLexerAdapter();
	}

	@Override
	public @NotNull PsiParser createParser(Project project) {
		return new TranslationsParser();
	}

	@Override
	public @NotNull IFileElementType getFileNodeType() {
		return FILE;
	}

	@Override
	public @NotNull TokenSet getCommentTokens() {
		return TranslationsTokenSets.COMMENTS;
	}

	@Override
	public @NotNull TokenSet getStringLiteralElements() {
		return TokenSet.EMPTY;
	}

	@Override
	public @NotNull PsiElement createElement(ASTNode node) {
		return TranslationsTypes.Factory.createElement(node);
	}

	@Override
	public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
		return new TranslationsFile(viewProvider);
	}
}
