package org.intellij.sdk.language.legacy.common.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.eclipse.sisu.inject.Legacy;import org.intellij.sdk.language.legacy.common.psi.LegacyTypes;

%%

%class LegacyLexer
%public
%implements FlexLexer
%unicode
%function advance
%type IElementType

%{
  private String legacySymbol = "§";
  private boolean useHashHex = true;

  public LegacyLexer(String legacySymbol, boolean useHashHex) {
      this.legacySymbol = legacySymbol;
      this.useHashHex = useHashHex;
      this.zzReader = null;
  }
%}

ESCAPE=\\
HEXCOLOR_HASH=#[0-9a-f]{6}
HEXCOLOR_X=x
COLOR=[0-9]|[a-f]
SPECIAL=r
DECO=[k-o]

%state ESC
%state FORMAT

%%

<YYINITIAL> {
    {ESCAPE} { yybegin(ESC); return LegacyTypes.MISC; }
    [^] {
        int loc = getTokenStart();
        char cur = zzBuffer.charAt(loc);
        if ((cur + "").equals(legacySymbol)) {
            yybegin(FORMAT);
            return LegacyTypes.SYMBOL;
        }
        return LegacyTypes.MISC;
    }
}

<FORMAT> {
    {DECO} { yybegin(YYINITIAL); return LegacyTypes.DECO; }
    {HEXCOLOR_HASH} { yybegin(YYINITIAL); return LegacyTypes.HEXCOLOR_HASH; }
    {HEXCOLOR_X} { yybegin(YYINITIAL); return LegacyTypes.HEXCOLOR_X; }
    {COLOR} { yybegin(YYINITIAL); return LegacyTypes.COLOR; }
    {SPECIAL} { yybegin(YYINITIAL); return LegacyTypes.SPECIAL; }
}

<ESC, FORMAT> {
    [^] { yybegin(YYINITIAL); return LegacyTypes.MISC; }
}
