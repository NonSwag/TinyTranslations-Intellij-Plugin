package org.intellij.sdk.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.AmpersandTypes;
import com.intellij.psi.TokenType;

%%

%class AmpersandLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

ESCAPE=\\
SYMBOL=&
HEXCOLOR=#[0-9a-f]{6}
COLOR=[0-9]|[a-f]
SPECIAL=r
DECO=[k-o]
MISC=[^]

%state ESC
%state AMP

%%

<YYINITIAL> {
    {ESCAPE} { yybegin(ESC); return AmpersandTypes.MISC; }
    {SYMBOL} { return AmpersandTypes.SYMBOL; }
    {DECO} { return AmpersandTypes.DECO; }
    {HEXCOLOR} { return AmpersandTypes.HEXCOLOR; }
    {COLOR} { return AmpersandTypes.COLOR; }
    {SPECIAL} { return AmpersandTypes.SPECIAL; }
    {MISC} { return AmpersandTypes.MISC; }
}

<ESC> {
    {MISC} { yybegin(YYINITIAL); return AmpersandTypes.MISC; }
}
