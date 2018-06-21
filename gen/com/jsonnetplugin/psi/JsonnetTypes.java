// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.jsonnetlugin.psi.impl.*;

public interface JsonnetTypes {

  IElementType PROPERTY = new JsonnetElementType("PROPERTY");

  IElementType COMMENT = new JsonnetTokenType("COMMENT");
  IElementType CRLF = new JsonnetTokenType("CRLF");
  IElementType KEY = new JsonnetTokenType("KEY");
  IElementType SEPARATOR = new JsonnetTokenType("SEPARATOR");
  IElementType VALUE = new JsonnetTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == PROPERTY) {
        return new JsonnetPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
