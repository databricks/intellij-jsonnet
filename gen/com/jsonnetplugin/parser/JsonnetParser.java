// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.jsonnetplugin.psi.JsonnetTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JsonnetParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == APPLY) {
      r = apply(b, 0);
    }
    else if (t == ARGS) {
      r = args(b, 0);
    }
    else if (t == ASSERT) {
      r = assert_$(b, 0);
    }
    else if (t == BINARYOP) {
      r = binaryop(b, 0);
    }
    else if (t == BIND) {
      r = bind(b, 0);
    }
    else if (t == BINSUFFIX) {
      r = binsuffix(b, 0);
    }
    else if (t == COMPSPEC) {
      r = compspec(b, 0);
    }
    else if (t == DOLLAR) {
      r = dollar(b, 0);
    }
    else if (t == EXPR) {
      r = expr(b, 0);
    }
    else if (t == EXPR_0) {
      r = expr0(b, 0);
    }
    else if (t == FIELD) {
      r = field(b, 0);
    }
    else if (t == FIELDNAME) {
      r = fieldname(b, 0);
    }
    else if (t == FORSPEC) {
      r = forspec(b, 0);
    }
    else if (t == H) {
      r = h(b, 0);
    }
    else if (t == IFSPEC) {
      r = ifspec(b, 0);
    }
    else if (t == INSUPER) {
      r = insuper(b, 0);
    }
    else if (t == MEMBER) {
      r = member(b, 0);
    }
    else if (t == OBJEXTEND) {
      r = objextend(b, 0);
    }
    else if (t == OBJINSIDE) {
      r = objinside(b, 0);
    }
    else if (t == OBJLOCAL) {
      r = objlocal(b, 0);
    }
    else if (t == PARAMS) {
      r = params(b, 0);
    }
    else if (t == SELECT) {
      r = select(b, 0);
    }
    else if (t == SELF) {
      r = self(b, 0);
    }
    else if (t == SLICE) {
      r = slice(b, 0);
    }
    else if (t == SLICE_2) {
      r = slice2(b, 0);
    }
    else if (t == UNARYOP) {
      r = unaryop(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // "(" ( args )? ")"
  public static boolean apply(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apply")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, APPLY, "<apply>");
    r = consumeToken(b, "(");
    r = r && apply_1(b, l + 1);
    r = r && consumeToken(b, ")");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( args )?
  private static boolean apply_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apply_1")) return false;
    apply_1_0(b, l + 1);
    return true;
  }

  // ( args )
  private static boolean apply_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "apply_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = args(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expr ( "," expr )* ( "," IDENTIFIER "=" expr )* ( "," )?
  // |	IDENTIFIER "=" expr ( "," IDENTIFIER "=" expr )* ( "," )?
  public static boolean args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGS, "<args>");
    r = args_0(b, l + 1);
    if (!r) r = args_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // expr ( "," expr )* ( "," IDENTIFIER "=" expr )* ( "," )?
  private static boolean args_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    r = r && args_0_1(b, l + 1);
    r = r && args_0_2(b, l + 1);
    r = r && args_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," expr )*
  private static boolean args_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!args_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "args_0_1", c)) break;
    }
    return true;
  }

  // "," expr
  private static boolean args_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," IDENTIFIER "=" expr )*
  private static boolean args_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!args_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "args_0_2", c)) break;
    }
    return true;
  }

  // "," IDENTIFIER "=" expr
  private static boolean args_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," )?
  private static boolean args_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_0_3")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // IDENTIFIER "=" expr ( "," IDENTIFIER "=" expr )* ( "," )?
  private static boolean args_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    r = r && args_1_3(b, l + 1);
    r = r && args_1_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," IDENTIFIER "=" expr )*
  private static boolean args_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_1_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!args_1_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "args_1_3", c)) break;
    }
    return true;
  }

  // "," IDENTIFIER "=" expr
  private static boolean args_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," )?
  private static boolean args_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "args_1_4")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // "assert" expr ( ":" expr )?
  public static boolean assert_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_$")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSERT, "<assert $>");
    r = consumeToken(b, "assert");
    r = r && expr(b, l + 1);
    r = r && assert_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ( ":" expr )?
  private static boolean assert_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_2")) return false;
    assert_2_0(b, l + 1);
    return true;
  }

  // ":" expr
  private static boolean assert_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assert_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "*" | "/" | "%" | "+" | "-" | "<<" | ">>" | "<" | "<=" | ">" | ">=" | "==" | "!=" | "in" | "&" | "^" | "|" | "&&" | "||"
  public static boolean binaryop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binaryop")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINARYOP, "<binaryop>");
    r = consumeToken(b, "*");
    if (!r) r = consumeToken(b, "/");
    if (!r) r = consumeToken(b, "%");
    if (!r) r = consumeToken(b, "+");
    if (!r) r = consumeToken(b, "-");
    if (!r) r = consumeToken(b, "<<");
    if (!r) r = consumeToken(b, ">>");
    if (!r) r = consumeToken(b, "<");
    if (!r) r = consumeToken(b, "<=");
    if (!r) r = consumeToken(b, ">");
    if (!r) r = consumeToken(b, ">=");
    if (!r) r = consumeToken(b, "==");
    if (!r) r = consumeToken(b, "!=");
    if (!r) r = consumeToken(b, "in");
    if (!r) r = consumeToken(b, "&");
    if (!r) r = consumeToken(b, "^");
    if (!r) r = consumeToken(b, "|");
    if (!r) r = consumeToken(b, "&&");
    if (!r) r = consumeToken(b, "||");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER "=" expr
  // |	IDENTIFIER "(" ( params )? ")" "=" expr
  public static boolean bind(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bind_0(b, l + 1);
    if (!r) r = bind_1(b, l + 1);
    exit_section_(b, m, BIND, r);
    return r;
  }

  // IDENTIFIER "=" expr
  private static boolean bind_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER "(" ( params )? ")" "=" expr
  private static boolean bind_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "(");
    r = r && bind_1_2(b, l + 1);
    r = r && consumeToken(b, ")");
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( params )?
  private static boolean bind_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_1_2")) return false;
    bind_1_2_0(b, l + 1);
    return true;
  }

  // ( params )
  private static boolean bind_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bind_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // binaryop expr
  public static boolean binsuffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binsuffix")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINSUFFIX, "<binsuffix>");
    r = binaryop(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ( forspec | ifspec )*
  public static boolean compspec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compspec")) return false;
    Marker m = enter_section_(b, l, _NONE_, COMPSPEC, "<compspec>");
    while (true) {
      int c = current_position_(b);
      if (!compspec_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "compspec", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // forspec | ifspec
  private static boolean compspec_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compspec_0")) return false;
    boolean r;
    r = forspec(b, l + 1);
    if (!r) r = ifspec(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // "$"
  public static boolean dollar(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dollar")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOLLAR, "<dollar>");
    r = consumeToken(b, "$");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expr0 select* slice* slice2* apply* binsuffix* objextend* insuper*
  public static boolean expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = expr0(b, l + 1);
    r = r && expr_1(b, l + 1);
    r = r && expr_2(b, l + 1);
    r = r && expr_3(b, l + 1);
    r = r && expr_4(b, l + 1);
    r = r && expr_5(b, l + 1);
    r = r && expr_6(b, l + 1);
    r = r && expr_7(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // select*
  private static boolean expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!select(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_1", c)) break;
    }
    return true;
  }

  // slice*
  private static boolean expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!slice(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_2", c)) break;
    }
    return true;
  }

  // slice2*
  private static boolean expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!slice2(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_3", c)) break;
    }
    return true;
  }

  // apply*
  private static boolean expr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!apply(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_4", c)) break;
    }
    return true;
  }

  // binsuffix*
  private static boolean expr_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!binsuffix(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_5", c)) break;
    }
    return true;
  }

  // objextend*
  private static boolean expr_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!objextend(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_6", c)) break;
    }
    return true;
  }

  // insuper*
  private static boolean expr_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!insuper(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr_7", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "null" | "true" | "false" | self | dollar | DOUBLE_QUOTED_STRING | NUMBER
  // |	"{" objinside "}"
  // |	"[" (expr ("," expr)* ","?)? "]"
  // |	"[" expr ","? forspec compspec "]"
  // //|	expr "." IDENTIFIER
  // //|	expr "[" expr ( ":" ( expr ( ":" ( expr )? )? )? )? "]"
  // //|	expr "[" ":" ( expr ( ":" ( expr )? )? )? "]"
  // |	"super" "." IDENTIFIER
  // |	"super" "[" expr "]"
  // //|	expr "(" ( args )? ")"
  // |	IDENTIFIER
  // |	"local" bind ( "," bind )* ";" expr
  // |	"if" expr "then" expr ( "else" expr )?
  // //|	expr binaryop expr
  // |	unaryop expr
  // //|	expr "{" objinside "}"
  // |	"function" "(" ( params )? ")" expr
  // |	assert ";" expr
  // |	"import" DOUBLE_QUOTED_STRING
  // |	"importstr" DOUBLE_QUOTED_STRING
  // |	"error" expr
  public static boolean expr0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR_0, "<expr 0>");
    r = consumeToken(b, NULL);
    if (!r) r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    if (!r) r = self(b, l + 1);
    if (!r) r = dollar(b, l + 1);
    if (!r) r = consumeToken(b, DOUBLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = expr0_7(b, l + 1);
    if (!r) r = expr0_8(b, l + 1);
    if (!r) r = expr0_9(b, l + 1);
    if (!r) r = expr0_10(b, l + 1);
    if (!r) r = expr0_11(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = expr0_13(b, l + 1);
    if (!r) r = expr0_14(b, l + 1);
    if (!r) r = expr0_15(b, l + 1);
    if (!r) r = expr0_16(b, l + 1);
    if (!r) r = expr0_17(b, l + 1);
    if (!r) r = expr0_18(b, l + 1);
    if (!r) r = expr0_19(b, l + 1);
    if (!r) r = expr0_20(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "{" objinside "}"
  private static boolean expr0_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_CURLY);
    r = r && objinside(b, l + 1);
    r = r && consumeToken(b, R_CURLY);
    exit_section_(b, m, null, r);
    return r;
  }

  // "[" (expr ("," expr)* ","?)? "]"
  private static boolean expr0_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && expr0_8_1(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (expr ("," expr)* ","?)?
  private static boolean expr0_8_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_8_1")) return false;
    expr0_8_1_0(b, l + 1);
    return true;
  }

  // expr ("," expr)* ","?
  private static boolean expr0_8_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_8_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    r = r && expr0_8_1_0_1(b, l + 1);
    r = r && expr0_8_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," expr)*
  private static boolean expr0_8_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_8_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expr0_8_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr0_8_1_0_1", c)) break;
    }
    return true;
  }

  // "," expr
  private static boolean expr0_8_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_8_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ","?
  private static boolean expr0_8_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_8_1_0_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // "[" expr ","? forspec compspec "]"
  private static boolean expr0_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_9")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && expr0_9_2(b, l + 1);
    r = r && forspec(b, l + 1);
    r = r && compspec(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // ","?
  private static boolean expr0_9_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_9_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // "super" "." IDENTIFIER
  private static boolean expr0_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_10")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "super");
    r = r && consumeToken(b, ".");
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // "super" "[" expr "]"
  private static boolean expr0_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_11")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "super");
    r = r && consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // "local" bind ( "," bind )* ";" expr
  private static boolean expr0_13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_13")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "local");
    r = r && bind(b, l + 1);
    r = r && expr0_13_2(b, l + 1);
    r = r && consumeToken(b, ";");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," bind )*
  private static boolean expr0_13_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_13_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expr0_13_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expr0_13_2", c)) break;
    }
    return true;
  }

  // "," bind
  private static boolean expr0_13_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_13_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && bind(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "if" expr "then" expr ( "else" expr )?
  private static boolean expr0_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_14")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "if");
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, "then");
    r = r && expr(b, l + 1);
    r = r && expr0_14_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "else" expr )?
  private static boolean expr0_14_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_14_4")) return false;
    expr0_14_4_0(b, l + 1);
    return true;
  }

  // "else" expr
  private static boolean expr0_14_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_14_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "else");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // unaryop expr
  private static boolean expr0_15(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_15")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unaryop(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "function" "(" ( params )? ")" expr
  private static boolean expr0_16(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_16")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "function");
    r = r && consumeToken(b, "(");
    r = r && expr0_16_2(b, l + 1);
    r = r && consumeToken(b, ")");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( params )?
  private static boolean expr0_16_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_16_2")) return false;
    expr0_16_2_0(b, l + 1);
    return true;
  }

  // ( params )
  private static boolean expr0_16_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_16_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // assert ";" expr
  private static boolean expr0_17(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_17")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = assert_$(b, l + 1);
    r = r && consumeToken(b, ";");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "import" DOUBLE_QUOTED_STRING
  private static boolean expr0_18(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_18")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "import");
    r = r && consumeToken(b, DOUBLE_QUOTED_STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  // "importstr" DOUBLE_QUOTED_STRING
  private static boolean expr0_19(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_19")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "importstr");
    r = r && consumeToken(b, DOUBLE_QUOTED_STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  // "error" expr
  private static boolean expr0_20(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr0_20")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "error");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // fieldname ( "+" )? h expr
  // |	fieldname "(" ( params )* ")" h expr
  public static boolean field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = field_0(b, l + 1);
    if (!r) r = field_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // fieldname ( "+" )? h expr
  private static boolean field_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fieldname(b, l + 1);
    r = r && field_0_1(b, l + 1);
    r = r && h(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "+" )?
  private static boolean field_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_0_1")) return false;
    field_0_1_0(b, l + 1);
    return true;
  }

  // ( "+" )
  private static boolean field_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "+");
    exit_section_(b, m, null, r);
    return r;
  }

  // fieldname "(" ( params )* ")" h expr
  private static boolean field_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fieldname(b, l + 1);
    r = r && consumeToken(b, "(");
    r = r && field_1_2(b, l + 1);
    r = r && consumeToken(b, ")");
    r = r && h(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( params )*
  private static boolean field_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!field_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "field_1_2", c)) break;
    }
    return true;
  }

  // ( params )
  private static boolean field_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | DOUBLE_QUOTED_STRING | "[" expr "]"
  public static boolean fieldname(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldname")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELDNAME, "<fieldname>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, DOUBLE_QUOTED_STRING);
    if (!r) r = fieldname_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "[" expr "]"
  private static boolean fieldname_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldname_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "for" IDENTIFIER "in" expr
  public static boolean forspec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forspec")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FORSPEC, "<forspec>");
    r = consumeToken(b, "for");
    r = r && consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "in");
    r = r && expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ":" | "::" | ":::"
  public static boolean h(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "h")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, H, "<h>");
    r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, "::");
    if (!r) r = consumeToken(b, ":::");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "if" expr
  public static boolean ifspec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifspec")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IFSPEC, "<ifspec>");
    r = consumeToken(b, "if");
    r = r && expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "in" "super"
  public static boolean insuper(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "insuper")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSUPER, "<insuper>");
    r = consumeToken(b, "in");
    r = r && consumeToken(b, "super");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // objlocal | assert | field
  public static boolean member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MEMBER, "<member>");
    r = objlocal(b, l + 1);
    if (!r) r = assert_$(b, l + 1);
    if (!r) r = field(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "{" objinside "}"
  public static boolean objextend(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objextend")) return false;
    if (!nextTokenIs(b, L_CURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_CURLY);
    r = r && objinside(b, l + 1);
    r = r && consumeToken(b, R_CURLY);
    exit_section_(b, m, OBJEXTEND, r);
    return r;
  }

  /* ********************************************************** */
  // member ( "," member )* ( "," )?
  // |	( objlocal "," )* "[" expr "]" ":" expr ( ( "," objlocal )* ( "," )? )? forspec compspec
  public static boolean objinside(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJINSIDE, "<objinside>");
    r = objinside_0(b, l + 1);
    if (!r) r = objinside_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // member ( "," member )* ( "," )?
  private static boolean objinside_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = member(b, l + 1);
    r = r && objinside_0_1(b, l + 1);
    r = r && objinside_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," member )*
  private static boolean objinside_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!objinside_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "objinside_0_1", c)) break;
    }
    return true;
  }

  // "," member
  private static boolean objinside_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && member(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," )?
  private static boolean objinside_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_0_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // ( objlocal "," )* "[" expr "]" ":" expr ( ( "," objlocal )* ( "," )? )? forspec compspec
  private static boolean objinside_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = objinside_1_0(b, l + 1);
    r = r && consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && consumeTokens(b, 0, R_BRACKET, COLON);
    r = r && expr(b, l + 1);
    r = r && objinside_1_6(b, l + 1);
    r = r && forspec(b, l + 1);
    r = r && compspec(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( objlocal "," )*
  private static boolean objinside_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!objinside_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "objinside_1_0", c)) break;
    }
    return true;
  }

  // objlocal ","
  private static boolean objinside_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = objlocal(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( ( "," objlocal )* ( "," )? )?
  private static boolean objinside_1_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_6")) return false;
    objinside_1_6_0(b, l + 1);
    return true;
  }

  // ( "," objlocal )* ( "," )?
  private static boolean objinside_1_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = objinside_1_6_0_0(b, l + 1);
    r = r && objinside_1_6_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," objlocal )*
  private static boolean objinside_1_6_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_6_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!objinside_1_6_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "objinside_1_6_0_0", c)) break;
    }
    return true;
  }

  // "," objlocal
  private static boolean objinside_1_6_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_6_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && objlocal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," )?
  private static boolean objinside_1_6_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objinside_1_6_0_1")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // "local" bind
  public static boolean objlocal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "objlocal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJLOCAL, "<objlocal>");
    r = consumeToken(b, "local");
    r = r && bind(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ( "," IDENTIFIER )* ( "," IDENTIFIER "=" expr )* ( "," )?
  // |	IDENTIFIER "=" expr ( "," IDENTIFIER "=" expr )? ( "," )?
  public static boolean params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = params_0(b, l + 1);
    if (!r) r = params_1(b, l + 1);
    exit_section_(b, m, PARAMS, r);
    return r;
  }

  // IDENTIFIER ( "," IDENTIFIER )* ( "," IDENTIFIER "=" expr )* ( "," )?
  private static boolean params_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && params_0_1(b, l + 1);
    r = r && params_0_2(b, l + 1);
    r = r && params_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," IDENTIFIER )*
  private static boolean params_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!params_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "params_0_1", c)) break;
    }
    return true;
  }

  // "," IDENTIFIER
  private static boolean params_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," IDENTIFIER "=" expr )*
  private static boolean params_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!params_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "params_0_2", c)) break;
    }
    return true;
  }

  // "," IDENTIFIER "=" expr
  private static boolean params_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," )?
  private static boolean params_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_0_3")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // IDENTIFIER "=" expr ( "," IDENTIFIER "=" expr )? ( "," )?
  private static boolean params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    r = r && params_1_3(b, l + 1);
    r = r && params_1_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," IDENTIFIER "=" expr )?
  private static boolean params_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_3")) return false;
    params_1_3_0(b, l + 1);
    return true;
  }

  // "," IDENTIFIER "=" expr
  private static boolean params_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    r = r && consumeToken(b, "=");
    r = r && expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( "," )?
  private static boolean params_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_4")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // expr
  static boolean root(PsiBuilder b, int l) {
    return expr(b, l + 1);
  }

  /* ********************************************************** */
  // "." IDENTIFIER
  public static boolean select(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "select")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELECT, "<select>");
    r = consumeToken(b, ".");
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "self"
  public static boolean self(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "self")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SELF, "<self>");
    r = consumeToken(b, "self");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // "[" expr ( ":" ( expr ( ":" ( expr )? )? )? )? "]"
  public static boolean slice(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && expr(b, l + 1);
    r = r && slice_2(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, SLICE, r);
    return r;
  }

  // ( ":" ( expr ( ":" ( expr )? )? )? )?
  private static boolean slice_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2")) return false;
    slice_2_0(b, l + 1);
    return true;
  }

  // ":" ( expr ( ":" ( expr )? )? )?
  private static boolean slice_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && slice_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( expr ( ":" ( expr )? )? )?
  private static boolean slice_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0_1")) return false;
    slice_2_0_1_0(b, l + 1);
    return true;
  }

  // expr ( ":" ( expr )? )?
  private static boolean slice_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    r = r && slice_2_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( ":" ( expr )? )?
  private static boolean slice_2_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0_1_0_1")) return false;
    slice_2_0_1_0_1_0(b, l + 1);
    return true;
  }

  // ":" ( expr )?
  private static boolean slice_2_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && slice_2_0_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( expr )?
  private static boolean slice_2_0_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0_1_0_1_0_1")) return false;
    slice_2_0_1_0_1_0_1_0(b, l + 1);
    return true;
  }

  // ( expr )
  private static boolean slice_2_0_1_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_2_0_1_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "[" ":" ( expr ( ":" ( expr )? )? )? "]"
  public static boolean slice2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, L_BRACKET, COLON);
    r = r && slice2_2(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, SLICE_2, r);
    return r;
  }

  // ( expr ( ":" ( expr )? )? )?
  private static boolean slice2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2_2")) return false;
    slice2_2_0(b, l + 1);
    return true;
  }

  // expr ( ":" ( expr )? )?
  private static boolean slice2_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    r = r && slice2_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( ":" ( expr )? )?
  private static boolean slice2_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2_2_0_1")) return false;
    slice2_2_0_1_0(b, l + 1);
    return true;
  }

  // ":" ( expr )?
  private static boolean slice2_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && slice2_2_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( expr )?
  private static boolean slice2_2_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2_2_0_1_0_1")) return false;
    slice2_2_0_1_0_1_0(b, l + 1);
    return true;
  }

  // ( expr )
  private static boolean slice2_2_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice2_2_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "-" | "+" | "!" | "~"
  public static boolean unaryop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryop")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNARYOP, "<unaryop>");
    r = consumeToken(b, "-");
    if (!r) r = consumeToken(b, "+");
    if (!r) r = consumeToken(b, "!");
    if (!r) r = consumeToken(b, "~");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
