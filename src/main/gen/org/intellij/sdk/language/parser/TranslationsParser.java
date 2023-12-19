// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.intellij.sdk.language.psi.TranslationsTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TranslationsParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return document(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(CONTENT_TAG, ELEMENT, PLACEHOLDER, SELF_CLOSING_TAG,
      TEXT_ELEMENT),
  };

  /* ********************************************************** */
  // BOOL | NUMBER | VALUE
  static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    boolean r;
    r = consumeToken(b, BOOL);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, VALUE);
    return r;
  }

  /* ********************************************************** */
  // (SEPARATOR attribute)*
  static boolean attributes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributes")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attributes_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attributes", c)) break;
    }
    return true;
  }

  // SEPARATOR attribute
  private static boolean attributes_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributes_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_OPEN TAG_END TAG_KEY TAG_CLOSE
  static boolean close_tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "close_tag")) return false;
    if (!nextTokenIs(b, TAG_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TAG_OPEN, TAG_END, TAG_KEY, TAG_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // element+
  public static boolean content(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT, "<content>");
    r = element(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "content", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // open_tag element* close_tag
  public static boolean content_tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content_tag")) return false;
    if (!nextTokenIs(b, TAG_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = open_tag(b, l + 1);
    r = r && content_tag_1(b, l + 1);
    r = r && close_tag(b, l + 1);
    exit_section_(b, m, CONTENT_TAG, r);
    return r;
  }

  // element*
  private static boolean content_tag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content_tag_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "content_tag_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // content <<eof>>
  static boolean document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = content(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // placeholder | self_closing_tag | content_tag | text_element
  public static boolean element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ELEMENT, "<element>");
    r = placeholder(b, l + 1);
    if (!r) r = self_closing_tag(b, l + 1);
    if (!r) r = content_tag(b, l + 1);
    if (!r) r = text_element(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TAG_OPEN TAG_KEY attributes TAG_CLOSE
  static boolean open_tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open_tag")) return false;
    if (!nextTokenIs(b, TAG_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TAG_OPEN, TAG_KEY);
    r = r && attributes(b, l + 1);
    r = r && consumeToken(b, TAG_CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !PH_CLOSE
  static boolean ph_recovery(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ph_recovery")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, PH_CLOSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PH_OPEN PH_KEY attributes PH_CLOSE
  public static boolean placeholder(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "placeholder")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PLACEHOLDER, "<placeholder>");
    r = consumeTokens(b, 0, PH_OPEN, PH_KEY);
    r = r && attributes(b, l + 1);
    r = r && consumeToken(b, PH_CLOSE);
    exit_section_(b, l, m, r, false, TranslationsParser::ph_recovery);
    return r;
  }

  /* ********************************************************** */
  // TAG_OPEN TAG_KEY attributes TAG_END TAG_CLOSE
  public static boolean self_closing_tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "self_closing_tag")) return false;
    if (!nextTokenIs(b, TAG_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, TAG_OPEN, TAG_KEY);
    r = r && attributes(b, l + 1);
    r = r && consumeTokens(b, 0, TAG_END, TAG_CLOSE);
    exit_section_(b, m, SELF_CLOSING_TAG, r);
    return r;
  }

  /* ********************************************************** */
  // MISC?
  public static boolean text_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_element")) return false;
    Marker m = enter_section_(b, l, _NONE_, TEXT_ELEMENT, "<text element>");
    consumeToken(b, MISC);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

}
