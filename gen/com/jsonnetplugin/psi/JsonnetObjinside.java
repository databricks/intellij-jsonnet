// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import null.null;

public interface JsonnetObjinside extends PsiElement {

  @Nullable
  JsonnetCompspec getCompspec();

  @NotNull
  List<null> getExprList();

  @Nullable
  JsonnetForspec getForspec();

  @NotNull
  List<JsonnetMember> getMemberList();

  @NotNull
  List<JsonnetObjlocal> getObjlocalList();

}
