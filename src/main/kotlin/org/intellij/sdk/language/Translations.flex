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
%eof{  return;
%eof}

// NBT_KEY=nbt
// LEGACY_KEY=legacy
// LEGACY_AMP_OPEN=legacy:'&'
// LEGACY_PAR_OPEN=legacy:'§'
// LEGACY_CHAR=(&|§)([0-9]|[a-f]|[k-o]|r|#[0-9a-f]{6})

SEPARATOR=:
TAG_OPEN=<
TAG_CLOSE=>
TAG_END=\/
PH_OPEN=[{]
PH_CLOSE=[}]
TAG_KEY=[a-zA-Z][a-zA-Z0-9\-_.]*
PH_KEY=[a-zA-Z][a-zA-Z0-9\-_.]*
BOOL=true|false
NUMBER=[0-9]+([\.,][0-9]+)?
VALUE=[\'][^\']+[\'] | [\"][^\"]+[\"] | [a-zA-Z0-9\-._]+
MISC=[^]
BAD_CHARACTER=[^]

%state TAG
%state TAG_KEYED
%state TAG_WAITING_ATTRIBUTE
%state TAG_WAITING_CLOSE
%state TAG_SELF_CLOSE
%state TAG_CLOSING
%state NBT
%state PH
%state PH_ATTR_SEP
%state PH_ATTR

%%

<YYINITIAL> {
    {TAG_OPEN}             { yybegin(TAG); return TranslationsTypes.TAG_OPEN; }
    {PH_OPEN}              { yybegin(PH); return TranslationsTypes.PH_OPEN; }
    {MISC}                 { return TranslationsTypes.MISC; }
}

<PH> {
    {PH_KEY}                  { yybegin(PH_ATTR_SEP); return TranslationsTypes.PH_KEY; }
}
<PH_ATTR_SEP> {
    {SEPARATOR}            { yybegin(PH_ATTR); return TranslationsTypes.SEPARATOR; }
    {PH_CLOSE}             { yybegin(YYINITIAL); return TranslationsTypes.PH_CLOSE; }
}
<PH_ATTR> {
    {BOOL}                 { yybegin(PH_ATTR_SEP); return TranslationsTypes.BOOL; }
    {NUMBER}               { yybegin(PH_ATTR_SEP); return TranslationsTypes.NUMBER; }
    {VALUE}                { yybegin(PH_ATTR_SEP); return TranslationsTypes.VALUE; }
}

<TAG> {
    {TAG_END}              { yybegin(TAG_CLOSING); return TranslationsTypes.TAG_END; }
    {TAG_KEY}                  { yybegin(TAG_KEYED); return TranslationsTypes.TAG_KEY; }
}

<TAG_CLOSING> {TAG_KEY}          { yybegin(TAG_WAITING_CLOSE); return TranslationsTypes.TAG_KEY; }

<TAG_KEYED> {
    {SEPARATOR}            { yybegin(TAG_WAITING_ATTRIBUTE); return TranslationsTypes.SEPARATOR; }
    {TAG_END}              { yybegin(TAG_WAITING_CLOSE); return TranslationsTypes.TAG_END; }
    {TAG_CLOSE}            { yybegin(YYINITIAL); return TranslationsTypes.TAG_CLOSE; }
}

<TAG_WAITING_CLOSE> {
    {TAG_CLOSE}            { yybegin(YYINITIAL); return TranslationsTypes.TAG_CLOSE; }
}

<TAG_WAITING_ATTRIBUTE> {
    {BOOL}                 { yybegin(TAG_KEYED); return TranslationsTypes.BOOL; }
    {NUMBER}               { yybegin(TAG_KEYED); return TranslationsTypes.NUMBER; }
    {VALUE}                { yybegin(TAG_KEYED); return TranslationsTypes.VALUE; }
}
[^]                        { return TokenType.BAD_CHARACTER; }