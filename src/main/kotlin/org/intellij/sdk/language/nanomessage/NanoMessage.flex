package org.intellij.sdk.language;

import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.nanomessage.psi.NanoMessageTypes;

%%

%class NanoMessageLexer
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
    {ESCAPE}               { yybegin(ESC); return NanoMessageTypes.ESCAPE; }
    {WHITESPACE}           { return NanoMessageTypes.WHITESPACE; }
    {SQUOTE}               { return NanoMessageTypes.SQUOTE; }
    {DQUOTE}               { return NanoMessageTypes.DQUOTE; }
    {CHOICE}               { return NanoMessageTypes.CHOICE; }
    {SEPARATOR}            { return NanoMessageTypes.SEPARATOR; }
    {PH_OPEN}              { return NanoMessageTypes.PH_OPEN; }
    {PH_CLOSE}             { return NanoMessageTypes.PH_CLOSE; }
    {TAG_OPEN}             { return NanoMessageTypes.TAG_OPEN; }
    {TAG_END}              { return NanoMessageTypes.TAG_END; }
    {TAG_CLOSE}            { return NanoMessageTypes.TAG_CLOSE; }
    {NUMBER}               { return NanoMessageTypes.NUMBER; }
    {LITERAL}               { return NanoMessageTypes.LITERAL; }
    {MISC}                 { return NanoMessageTypes.MISC; }
}
<ESC> {
    [^] { yybegin(YYINITIAL); return NanoMessageTypes.MISC; }
}
