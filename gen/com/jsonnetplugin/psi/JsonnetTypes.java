// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.jsonnetlugin.psi.impl.*;

public interface JsonnetTypes {

  IElementType APPLY = new JsonnetElementType("APPLY");
  IElementType ARGS = new JsonnetElementType("ARGS");
  IElementType ASSERT = new JsonnetElementType("ASSERT");
  IElementType BINARYOP = new JsonnetElementType("BINARYOP");
  IElementType BIND = new JsonnetElementType("BIND");
  IElementType BINSUFFIX = new JsonnetElementType("BINSUFFIX");
  IElementType COMPSPEC = new JsonnetElementType("COMPSPEC");
  IElementType DOLLAR = new JsonnetElementType("DOLLAR");
  IElementType EXPR = new JsonnetElementType("EXPR");
  IElementType EXPR_0 = new JsonnetElementType("EXPR_0");
  IElementType FIELD = new JsonnetElementType("FIELD");
  IElementType FIELDNAME = new JsonnetElementType("FIELDNAME");
  IElementType FORSPEC = new JsonnetElementType("FORSPEC");
  IElementType H = new JsonnetElementType("H");
  IElementType IFSPEC = new JsonnetElementType("IFSPEC");
  IElementType INSUPER = new JsonnetElementType("INSUPER");
  IElementType MEMBER = new JsonnetElementType("MEMBER");
  IElementType OBJEXTEND = new JsonnetElementType("OBJEXTEND");
  IElementType OBJINSIDE = new JsonnetElementType("OBJINSIDE");
  IElementType OBJLOCAL = new JsonnetElementType("OBJLOCAL");
  IElementType PARAMS = new JsonnetElementType("PARAMS");
  IElementType SELECT = new JsonnetElementType("SELECT");
  IElementType SELF = new JsonnetElementType("SELF");
  IElementType SLICE = new JsonnetElementType("SLICE");
  IElementType SLICE_2 = new JsonnetElementType("SLICE_2");
  IElementType UNARYOP = new JsonnetElementType("UNARYOP");

  IElementType BLOCK_COMMENT = new JsonnetTokenType("BLOCK_COMMENT");
  IElementType COLON = new JsonnetTokenType(":");
  IElementType COMMA = new JsonnetTokenType(",");
  IElementType DOUBLE_QUOTED_STRING = new JsonnetTokenType("DOUBLE_QUOTED_STRING");
  IElementType FALSE = new JsonnetTokenType("false");
  IElementType IDENTIFIER = new JsonnetTokenType("IDENTIFIER");
  IElementType LINE_COMMENT = new JsonnetTokenType("LINE_COMMENT");
  IElementType L_BRACKET = new JsonnetTokenType("[");
  IElementType L_CURLY = new JsonnetTokenType("{");
  IElementType NULL = new JsonnetTokenType("null");
  IElementType NUMBER = new JsonnetTokenType("NUMBER");
  IElementType R_BRACKET = new JsonnetTokenType("]");
  IElementType R_CURLY = new JsonnetTokenType("}");
  IElementType SINGLE_QUOTED_STRING = new JsonnetTokenType("SINGLE_QUOTED_STRING");
  IElementType STRING = new JsonnetTokenType("STRING");
  IElementType TRUE = new JsonnetTokenType("true");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == APPLY) {
        return new JsonnetApplyImpl(node);
      }
      else if (type == ARGS) {
        return new JsonnetArgsImpl(node);
      }
      else if (type == ASSERT) {
        return new JsonnetAssertImpl(node);
      }
      else if (type == BINARYOP) {
        return new JsonnetBinaryopImpl(node);
      }
      else if (type == BIND) {
        return new JsonnetBindImpl(node);
      }
      else if (type == BINSUFFIX) {
        return new JsonnetBinsuffixImpl(node);
      }
      else if (type == COMPSPEC) {
        return new JsonnetCompspecImpl(node);
      }
      else if (type == DOLLAR) {
        return new JsonnetDollarImpl(node);
      }
      else if (type == EXPR) {
        return new JsonnetExprImpl(node);
      }
      else if (type == EXPR_0) {
        return new JsonnetExpr0Impl(node);
      }
      else if (type == FIELD) {
        return new JsonnetFieldImpl(node);
      }
      else if (type == FIELDNAME) {
        return new JsonnetFieldnameImpl(node);
      }
      else if (type == FORSPEC) {
        return new JsonnetForspecImpl(node);
      }
      else if (type == H) {
        return new JsonnetHImpl(node);
      }
      else if (type == IFSPEC) {
        return new JsonnetIfspecImpl(node);
      }
      else if (type == INSUPER) {
        return new JsonnetInsuperImpl(node);
      }
      else if (type == MEMBER) {
        return new JsonnetMemberImpl(node);
      }
      else if (type == OBJEXTEND) {
        return new JsonnetObjextendImpl(node);
      }
      else if (type == OBJINSIDE) {
        return new JsonnetObjinsideImpl(node);
      }
      else if (type == OBJLOCAL) {
        return new JsonnetObjlocalImpl(node);
      }
      else if (type == PARAMS) {
        return new JsonnetParamsImpl(node);
      }
      else if (type == SELECT) {
        return new JsonnetSelectImpl(node);
      }
      else if (type == SELF) {
        return new JsonnetSelfImpl(node);
      }
      else if (type == SLICE) {
        return new JsonnetSliceImpl(node);
      }
      else if (type == SLICE_2) {
        return new JsonnetSlice2Impl(node);
      }
      else if (type == UNARYOP) {
        return new JsonnetUnaryopImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
