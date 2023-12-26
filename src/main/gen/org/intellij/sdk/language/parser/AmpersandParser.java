// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.intellij.sdk.language.psi.AmpersandTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class AmpersandParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
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

  /* ********************************************************** */
  // COLOR
  public static boolean color_format(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "color_format")) return false;
    if (!nextTokenIs(b, COLOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLOR);
    exit_section_(b, m, COLOR_FORMAT, r);
    return r;
  }

  /* ********************************************************** */
  // color_format | hexcolor_format | special_format
  public static boolean color_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "color_group")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COLOR_GROUP, "<color group>");
    r = color_format(b, l + 1);
    if (!r) r = hexcolor_format(b, l + 1);
    if (!r) r = special_format(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // formatter | text
  static boolean content(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content")) return false;
    boolean r;
    r = formatter(b, l + 1);
    if (!r) r = text(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DECO
  public static boolean deco_format(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deco_format")) return false;
    if (!nextTokenIs(b, DECO)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DECO);
    exit_section_(b, m, DECO_FORMAT, r);
    return r;
  }

  /* ********************************************************** */
  // deco_format | special_format
  public static boolean deco_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deco_group")) return false;
    if (!nextTokenIs(b, "<deco group>", DECO, SPECIAL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECO_GROUP, "<deco group>");
    r = deco_format(b, l + 1);
    if (!r) r = special_format(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // content+ <<eof>>
  static boolean document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = document_0(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // content+
  private static boolean document_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = content(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!content(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "document_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // color_group | deco_group
  public static boolean format(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "format")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FORMAT, "<format>");
    r = color_group(b, l + 1);
    if (!r) r = deco_group(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SYMBOL format
  public static boolean formatter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "formatter")) return false;
    if (!nextTokenIs(b, SYMBOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYMBOL);
    r = r && format(b, l + 1);
    exit_section_(b, m, FORMATTER, r);
    return r;
  }

  /* ********************************************************** */
  // HEXCOLOR
  public static boolean hexcolor_format(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hexcolor_format")) return false;
    if (!nextTokenIs(b, HEXCOLOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HEXCOLOR);
    exit_section_(b, m, HEXCOLOR_FORMAT, r);
    return r;
  }

  /* ********************************************************** */
  // SPECIAL
  public static boolean special_format(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "special_format")) return false;
    if (!nextTokenIs(b, SPECIAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SPECIAL);
    exit_section_(b, m, SPECIAL_FORMAT, r);
    return r;
  }

  /* ********************************************************** */
  // MISC | ESCAPE | format | SYMBOL
  public static boolean text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEXT, "<text>");
    r = consumeToken(b, MISC);
    if (!r) r = consumeToken(b, ESCAPE);
    if (!r) r = format(b, l + 1);
    if (!r) r = consumeToken(b, SYMBOL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
