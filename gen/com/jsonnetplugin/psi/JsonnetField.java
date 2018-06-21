// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import null.null;

public interface JsonnetField extends PsiElement {

  @NotNull
  null getExpr();

  @NotNull
  JsonnetFieldname getFieldname();

  @NotNull
  JsonnetH getH();

  @NotNull
  List<JsonnetParams> getParamsList();

}
