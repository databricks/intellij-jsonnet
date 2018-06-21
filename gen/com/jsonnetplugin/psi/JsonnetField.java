// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetField extends PsiElement {

  @NotNull
  JsonnetExpr getExpr();

  @NotNull
  JsonnetFieldname getFieldname();

  @NotNull
  JsonnetH getH();

  @NotNull
  List<JsonnetParams> getParamsList();

}
