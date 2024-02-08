package org.intellij.sdk.language.nanomessage.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.XmlElementFactory;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlToken;
import com.intellij.psi.xml.XmlTokenType;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes;

%%

%class NanoMessageLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITESPACE=[ \t\n]
SEPARATOR=:
TAG_START=<
EMPTY_TAG_CLOSE=\/>
END_TAG_START=<\/
TAG_CLOSE=>
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
%state OPEN
%state CLOSE
%state KEY
%%

<YYINITIAL> {
    {ESCAPE}               { yybegin(ESC); return NanoMessageTypes.ESCAPE; }
    {WHITESPACE}           { return XmlTokenType.XML_WHITE_SPACE; }
    {SQUOTE}               { return NanoMessageTypes.SQUOTE; }
    {DQUOTE}               { return NanoMessageTypes.DQUOTE; }
    {CHOICE}               { return NanoMessageTypes.CHOICE; }
    {SEPARATOR}            { return NanoMessageTypes.SEPARATOR; }
    {PH_OPEN}              { return NanoMessageTypes.PH_OPEN; }
    {PH_CLOSE}             { return NanoMessageTypes.PH_CLOSE; }
    {EMPTY_TAG_CLOSE}      { return XmlTokenType.XML_EMPTY_ELEMENT_END; }
    {END_TAG_START}        { yybegin(KEY); return XmlTokenType.XML_END_TAG_START; }
    {TAG_START}            { yybegin(KEY); return XmlTokenType.XML_START_TAG_START; }
    {TAG_CLOSE}            { return XmlTokenType.XML_TAG_END; }
    {NUMBER}               { return NanoMessageTypes.NUMBER; }
    {LITERAL}              { return NanoMessageTypes.LITERAL; }
    {MISC}                 { return NanoMessageTypes.MISC; }
}
<KEY> {
    {LITERAL}              { yybegin(YYINITIAL); return XmlTokenType.XML_TAG_NAME; }
    [^] { yybegin(YYINITIAL); yypushback(yylength()); }
}
<ESC> {
    [^] { yybegin(YYINITIAL); return NanoMessageTypes.MISC; }
}
