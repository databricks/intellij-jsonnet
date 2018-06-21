// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.jsonnetlugin.psi.impl.*;

public interface JsonnetTypes {

  IElementType ARGS = new JsonnetElementType("ARGS");
  IElementType ASSERT = new JsonnetElementType("ASSERT");
  IElementType BINARYOP = new JsonnetElementType("BINARYOP");
  IElementType BIND = new JsonnetElementType("BIND");
  IElementType COMPSPEC = new JsonnetElementType("COMPSPEC");
  IElementType DOLLAR = new JsonnetElementType("DOLLAR");
  IElementType EXPR = new JsonnetElementType("EXPR");
  IElementType FALSE = new JsonnetElementType("FALSE");
  IElementType FIELD = new JsonnetElementType("FIELD");
  IElementType FIELDNAME = new JsonnetElementType("FIELDNAME");
  IElementType FORSPEC = new JsonnetElementType("FORSPEC");
  IElementType H = new JsonnetElementType("H");
  IElementType IFSPEC = new JsonnetElementType("IFSPEC");
  IElementType MEMBER = new JsonnetElementType("MEMBER");
  IElementType NULL = new JsonnetElementType("NULL");
  IElementType OBJINSIDE = new JsonnetElementType("OBJINSIDE");
  IElementType OBJLOCAL = new JsonnetElementType("OBJLOCAL");
  IElementType PARAMS = new JsonnetElementType("PARAMS");
  IElementType SELF = new JsonnetElementType("SELF");
  IElementType TRUE = new JsonnetElementType("TRUE");
  IElementType UNARYOP = new JsonnetElementType("UNARYOP");

  IElementType DOUBLE_QUOTED_STRING = new JsonnetTokenType("DOUBLE_QUOTED_STRING");
  IElementType IDENTIFIER = new JsonnetTokenType("IDENTIFIER");
  IElementType NUMBER = new JsonnetTokenType("NUMBER");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ARGS) {
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
      else if (type == COMPSPEC) {
        return new JsonnetCompspecImpl(node);
      }
      else if (type == DOLLAR) {
        return new JsonnetDollarImpl(node);
      }
      else if (type == EXPR) {
        return new JsonnetExprImpl(node);
      }
      else if (type == FALSE) {
        return new JsonnetFalseImpl(node);
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
      else if (type == MEMBER) {
        return new JsonnetMemberImpl(node);
      }
      else if (type == NULL) {
        return new JsonnetNullImpl(node);
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
      else if (type == SELF) {
        return new JsonnetSelfImpl(node);
      }
      else if (type == TRUE) {
        return new JsonnetTrueImpl(node);
      }
      else if (type == UNARYOP) {
        return new JsonnetUnaryopImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
