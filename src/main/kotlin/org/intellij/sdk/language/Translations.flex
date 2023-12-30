package org.intellij.sdk.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.TranslationsTypes;
import com.intellij.psi.TokenType;

%%

%class TranslationsLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITESPACE=[ \t\n]
SEPARATOR=:
TAG_OPEN=<
TAG_CLOSE=>
TAG_END=\/
CHOICE=\?
ESCAPE=\\
PH_OPEN=[{]
PH_CLOSE=[}]
NUMBER=[0-9]+([\.,][0-9]+)?
LITERAL=[a-zA-Z0-9\-_.#]+
SQUOTE=\'
DQUOTE=\"
MISC=[^]

%state ESC

%%

<YYINITIAL> {
    {ESCAPE}               { yybegin(ESC); return TranslationsTypes.ESCAPE; }
    {WHITESPACE}           { return TranslationsTypes.WHITESPACE; }
    {SQUOTE}               { return TranslationsTypes.SQUOTE; }
    {DQUOTE}               { return TranslationsTypes.DQUOTE; }
    {CHOICE}               { return TranslationsTypes.CHOICE; }
    {SEPARATOR}            { return TranslationsTypes.SEPARATOR; }
    {PH_OPEN}              { return TranslationsTypes.PH_OPEN; }
    {PH_CLOSE}             { return TranslationsTypes.PH_CLOSE; }
    {TAG_OPEN}             { return TranslationsTypes.TAG_OPEN; }
    {TAG_END}              { return TranslationsTypes.TAG_END; }
    {TAG_CLOSE}            { return TranslationsTypes.TAG_CLOSE; }
    {NUMBER}               { return TranslationsTypes.NUMBER; }
    {LITERAL}               { return TranslationsTypes.LITERAL; }
    {MISC}                 { return TranslationsTypes.MISC; }
}
<ESC> {
    [^] { yybegin(YYINITIAL); return TranslationsTypes.MISC; }
}
