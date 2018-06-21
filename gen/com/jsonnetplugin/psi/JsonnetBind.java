// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetBind extends PsiElement {

  @NotNull
  JsonnetExpr getExpr();

  @Nullable
  JsonnetParams getParams();

  @NotNull
  PsiElement getIdentifier();

}
