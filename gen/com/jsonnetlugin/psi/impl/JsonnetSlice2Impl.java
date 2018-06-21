// This is a generated file. Not intended for manual editing.
package com.jsonnetlugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.jsonnetplugin.psi.JsonnetTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.jsonnetplugin.psi.*;

public class JsonnetSlice2Impl extends ASTWrapperPsiElement implements JsonnetSlice2 {

  public JsonnetSlice2Impl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitSlice2(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JsonnetExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetExpr.class);
  }

}
