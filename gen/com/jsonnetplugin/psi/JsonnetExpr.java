// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetExpr extends PsiElement {

  @Nullable
  JsonnetArgs getArgs();

  @Nullable
  JsonnetAssert getAssert();

  @Nullable
  JsonnetBinaryop getBinaryop();

  @NotNull
  List<JsonnetBind> getBindList();

  @Nullable
  JsonnetCompspec getCompspec();

  @Nullable
  JsonnetDollar getDollar();

  @NotNull
  List<JsonnetExpr> getExprList();

  @Nullable
  JsonnetFalse getFalse();

  @Nullable
  JsonnetForspec getForspec();

  @Nullable
  JsonnetNull getNull();

  @Nullable
  JsonnetObjinside getObjinside();

  @Nullable
  JsonnetParams getParams();

  @Nullable
  JsonnetSelf getSelf();

  @Nullable
  JsonnetTrue getTrue();

  @Nullable
  JsonnetUnaryop getUnaryop();

}
