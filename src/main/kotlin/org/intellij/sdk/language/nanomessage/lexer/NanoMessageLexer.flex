/* It's an automatically generated code. Do not modify it. */
package org.intellij.sdk.language.nanomessage.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.*;
import com.intellij.psi.xml.*;import org.intellij.sdk.language.minimessage.MiniMessageTokenType;import org.intellij.sdk.language.nanomessage.NanoMessageTokenType;

%%

%unicode

%{
  public NanoMessageLexer() {
    this((java.io.Reader)null);
  }
%}

%class NanoMessageLexer
%public
%implements FlexLexer
%function advance
%type IElementType
//%debug

%state COMMENT
%state START_TAG_NAME
%state END_TAG_NAME
%state TAG_ATTRIBUTES
%state ATTRIBUTE_VALUE_START
%state ATTRIBUTE_VALUE
%state ATTRIBUTE_VALUE_DQ
%state ATTRIBUTE_VALUE_SQ
%state TAG_CHARACTERS
/* IMPORTANT! number of states should not exceed 16. See JspHighlightingLexer. */

ALPHA=[:letter:]
DIGIT=[0-9]
WHITE_SPACE_CHARS=[ \n\r\t\f\u2028\u2029\u0085]+

TAG_NAME=({ALPHA}|{DIGIT}|"_"|"-"|"#"|"!"|"?")({ALPHA}|{DIGIT}|"_"|"-")*
/* see http://www.w3.org/TR/html5/syntax.html#syntax-attribute-name */
ATTRIBUTE_SEPARATOR=":"

END_COMMENT="-->"
%%


<YYINITIAL> {WHITE_SPACE_CHARS} { return XmlTokenType.XML_REAL_WHITE_SPACE; }
<TAG_ATTRIBUTES,ATTRIBUTE_VALUE_START, START_TAG_NAME, END_TAG_NAME, TAG_CHARACTERS> {WHITE_SPACE_CHARS} { return XmlTokenType.XML_WHITE_SPACE; }
<YYINITIAL> ("<" | "{") {TAG_NAME} { yybegin(START_TAG_NAME); yypushback(yylength()); }
<START_TAG_NAME, TAG_CHARACTERS> "<" { return XmlTokenType.XML_START_TAG_START; }
<START_TAG_NAME, TAG_CHARACTERS> "{" { return NanoMessageTokenType.NM_PLACEHOLDER_START; }

<YYINITIAL> "</" {TAG_NAME} { yybegin(END_TAG_NAME); yypushback(yylength()); }
<YYINITIAL, END_TAG_NAME> "</" { return XmlTokenType.XML_END_TAG_START; }

<YYINITIAL> "<!--" { yybegin(COMMENT); return XmlTokenType.XML_COMMENT_START; }
<COMMENT> {END_COMMENT} | "<!-->" { yybegin(YYINITIAL); return XmlTokenType.XML_COMMENT_END; }
<COMMENT> "<!--" { return XmlTokenType.XML_BAD_CHARACTER; }
<COMMENT> "<!--->" | "--!>" { yybegin(YYINITIAL); return XmlTokenType.XML_BAD_CHARACTER; }
<COMMENT> ">" {
  // according to HTML spec (http://www.w3.org/html/wg/drafts/html/master/syntax.html#comments)
  // comments should start with <!-- and end with -->. The comment <!--> is not valid, but should terminate
  // comment token. Please note that it's not true for XML (http://www.w3.org/TR/REC-xml/#sec-comments)
  int loc = getTokenStart();
  char prev = zzBuffer.charAt(loc - 1);
  char prevPrev = zzBuffer.charAt(loc - 2);
  if (prev == '-' && prevPrev == '-') {
    yybegin(YYINITIAL); return XmlTokenType.XML_BAD_CHARACTER;
  }
  return XmlTokenType.XML_COMMENT_CHARACTERS;
}
<COMMENT> [^] { return XmlTokenType.XML_COMMENT_CHARACTERS; }

<START_TAG_NAME, END_TAG_NAME> {TAG_NAME} { yybegin(TAG_ATTRIBUTES); return XmlTokenType.XML_NAME; }

<TAG_ATTRIBUTES, TAG_CHARACTERS> "/>" { yybegin(YYINITIAL); return XmlTokenType.XML_EMPTY_ELEMENT_END; }
<TAG_ATTRIBUTES, TAG_CHARACTERS> "}" { yybegin(YYINITIAL); return NanoMessageTokenType.NM_PLACEHOLDER_END; }
<TAG_ATTRIBUTES, TAG_CHARACTERS> ">" { yybegin(YYINITIAL); return XmlTokenType.XML_TAG_END; }
<TAG_ATTRIBUTES, ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_START> ":" { yybegin(ATTRIBUTE_VALUE_START); return MiniMessageTokenType.MM_ATTRIBUTE_SEPARATOR; }
<TAG_ATTRIBUTES, ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_START> "?" { yybegin(ATTRIBUTE_VALUE_START); return NanoMessageTokenType.NM_CHOICE_MARKER; }
<TAG_ATTRIBUTES, START_TAG_NAME, END_TAG_NAME> [^] { yybegin(YYINITIAL); yypushback(1); break; }

<TAG_CHARACTERS> [^] { return XmlTokenType.XML_TAG_CHARACTERS; }

<ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_START> ">" { yybegin(YYINITIAL); return XmlTokenType.XML_TAG_END; }
<ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_START> "/>" { yybegin(YYINITIAL); return XmlTokenType.XML_EMPTY_ELEMENT_END; }
<ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_START> "}" { yybegin(YYINITIAL); return NanoMessageTokenType.NM_PLACEHOLDER_END; }

<ATTRIBUTE_VALUE_START> "\"" { yybegin(ATTRIBUTE_VALUE_DQ); return XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER; }
<ATTRIBUTE_VALUE_START> "'" { yybegin(ATTRIBUTE_VALUE_SQ); return XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER; }
<ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_START> [^:'\"\>\}?] { yybegin(ATTRIBUTE_VALUE); return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN; }

<ATTRIBUTE_VALUE_DQ> {
  "\"" { yybegin(TAG_ATTRIBUTES); return XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER; }
  \\\$ { return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN; }
  [^] { return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN;}
}

<ATTRIBUTE_VALUE_SQ> {
  "'" { yybegin(TAG_ATTRIBUTES); return XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER; }
  \\\$ { return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN; }
  [^] { return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN;}
}

<YYINITIAL> [^] { return XmlTokenType.XML_DATA_CHARACTERS; }
[^] { return XmlTokenType.XML_BAD_CHARACTER; }