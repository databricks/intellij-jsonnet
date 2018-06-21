// This is a generated file. Not intended for manual editing.
package com.jsonnetplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JsonnetExpr extends PsiElement {

  @NotNull
  List<JsonnetApply> getApplyList();

  @NotNull
  List<JsonnetBinsuffix> getBinsuffixList();

  @NotNull
  JsonnetExpr0 getExpr0();

  @NotNull
  List<JsonnetInsuper> getInsuperList();

  @NotNull
  List<JsonnetObjextend> getObjextendList();

  @NotNull
  List<JsonnetSelect> getSelectList();

  @NotNull
  List<JsonnetSlice> getSliceList();

  @NotNull
  List<JsonnetSlice2> getSlice2List();

}
