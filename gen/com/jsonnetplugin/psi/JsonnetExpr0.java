// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetExpr0 extends PsiElement {

  @Nullable
  JsonnetAssert getAssert();

  @NotNull
  List<JsonnetBind> getBindList();

  @Nullable
  JsonnetCompspec getCompspec();

  @Nullable
  JsonnetDollar getDollar();

  @NotNull
  List<JsonnetExpr> getExprList();

  @Nullable
  JsonnetForspec getForspec();

  @Nullable
  JsonnetObjinside getObjinside();

  @Nullable
  JsonnetParams getParams();

  @Nullable
  JsonnetSelf getSelf();

  @Nullable
  JsonnetUnaryop getUnaryop();

  @Nullable
  PsiElement getDoubleQuotedString();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getNumber();

}
