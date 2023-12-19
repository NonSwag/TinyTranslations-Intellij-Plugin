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
FORMAT=&(r|[0-9]|[a-f]|[k-o]|#[0-9a-f]{6})
MISC=[^]

%state ESC
%state AMP

%%

<YYINITIAL> {
    {ESCAPE} { yybegin(ESC); return AmpersandTypes.MISC; }
    {FORMAT} { return AmpersandTypes.FORMAT; }
    {MISC} { return AmpersandTypes.MISC; }
}

<ESC> {
    {MISC} { yybegin(YYINITIAL); return AmpersandTypes.MISC; }
}
