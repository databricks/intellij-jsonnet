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

public class JsonnetFieldImpl extends ASTWrapperPsiElement implements JsonnetField {

  public JsonnetFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitField(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JsonnetExpr getExpr() {
    return findNotNullChildByClass(JsonnetExpr.class);
  }

  @Override
  @NotNull
  public JsonnetFieldname getFieldname() {
    return findNotNullChildByClass(JsonnetFieldname.class);
  }

  @Override
  @NotNull
  public JsonnetH getH() {
    return findNotNullChildByClass(JsonnetH.class);
  }

  @Override
  @NotNull
  public List<JsonnetParams> getParamsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetParams.class);
  }

}
