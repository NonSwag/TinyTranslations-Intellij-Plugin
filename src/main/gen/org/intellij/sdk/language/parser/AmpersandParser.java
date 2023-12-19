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
  // FORMAT | MISC
  public static boolean content(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content")) return false;
    if (!nextTokenIs(b, "<content>", FORMAT, MISC)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT, "<content>");
    r = consumeToken(b, FORMAT);
    if (!r) r = consumeToken(b, MISC);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // content+ <<eof>>
  static boolean document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document")) return false;
    if (!nextTokenIs(b, "", FORMAT, MISC)) return false;
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

}
