// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.legacy.common.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.intellij.sdk.language.legacy.common.psi.LegacyTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LegacyParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return document(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // COLOR
  static boolean color_format(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, COLOR);
  }

  /* ********************************************************** */
  // color_format | hexcolor_format | special_format
  static boolean color_group(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "color_group")) return false;
    boolean result_;
    result_ = color_format(builder_, level_ + 1);
    if (!result_) result_ = hexcolor_format(builder_, level_ + 1);
    if (!result_) result_ = special_format(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // formatter | string
  public static boolean content(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "content")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONTENT, "<content>");
    result_ = formatter(builder_, level_ + 1);
    if (!result_) result_ = string(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // DECO
  static boolean deco_format(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, DECO);
  }

  /* ********************************************************** */
  // deco_format | special_format
  static boolean deco_group(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "deco_group")) return false;
    if (!nextTokenIs(builder_, "", DECO, SPECIAL)) return false;
    boolean result_;
    result_ = deco_format(builder_, level_ + 1);
    if (!result_) result_ = special_format(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // content* <<eof>>
  static boolean document(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "document")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = document_0(builder_, level_ + 1);
    result_ = result_ && eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // content*
  private static boolean document_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "document_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!content(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "document_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // color_group | deco_group
  static boolean format(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "format")) return false;
    boolean result_;
    result_ = color_group(builder_, level_ + 1);
    if (!result_) result_ = deco_group(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // SYMBOL format
  public static boolean formatter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "formatter")) return false;
    if (!nextTokenIs(builder_, SYMBOL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SYMBOL);
    result_ = result_ && format(builder_, level_ + 1);
    exit_section_(builder_, marker_, FORMATTER, result_);
    return result_;
  }

  /* ********************************************************** */
  // HEXCOLOR_HASH | (HEXCOLOR_X color_group{6})
  static boolean hexcolor_format(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "hexcolor_format")) return false;
    if (!nextTokenIs(builder_, "", HEXCOLOR_HASH, HEXCOLOR_X)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, HEXCOLOR_HASH);
    if (!result_) result_ = hexcolor_format_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // HEXCOLOR_X color_group{6}
  private static boolean hexcolor_format_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "hexcolor_format_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, HEXCOLOR_X);
    result_ = result_ && color_group(builder_, level_ + 1);
    result_ = result_ && hexcolor_format_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // {6}
  private static boolean hexcolor_format_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "hexcolor_format_1_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "6");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SPECIAL
  static boolean special_format(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, SPECIAL);
  }

  /* ********************************************************** */
  // MISC | ESCAPE | format | SYMBOL
  public static boolean string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRING, "<string>");
    result_ = consumeToken(builder_, MISC);
    if (!result_) result_ = consumeToken(builder_, ESCAPE);
    if (!result_) result_ = format(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, SYMBOL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

}
