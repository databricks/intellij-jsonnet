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

public class JsonnetExprImpl extends ASTWrapperPsiElement implements JsonnetExpr {

  public JsonnetExprImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitExpr(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsonnetArgs getArgs() {
    return findChildByClass(JsonnetArgs.class);
  }

  @Override
  @Nullable
  public JsonnetAssert getAssert() {
    return findChildByClass(JsonnetAssert.class);
  }

  @Override
  @Nullable
  public JsonnetBinaryop getBinaryop() {
    return findChildByClass(JsonnetBinaryop.class);
  }

  @Override
  @NotNull
  public List<JsonnetBind> getBindList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetBind.class);
  }

  @Override
  @Nullable
  public JsonnetCompspec getCompspec() {
    return findChildByClass(JsonnetCompspec.class);
  }

  @Override
  @Nullable
  public JsonnetDollar getDollar() {
    return findChildByClass(JsonnetDollar.class);
  }

  @Override
  @NotNull
  public List<JsonnetExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsonnetExpr.class);
  }

  @Override
  @Nullable
  public JsonnetFalse getFalse() {
    return findChildByClass(JsonnetFalse.class);
  }

  @Override
  @Nullable
  public JsonnetForspec getForspec() {
    return findChildByClass(JsonnetForspec.class);
  }

  @Override
  @Nullable
  public JsonnetNull getNull() {
    return findChildByClass(JsonnetNull.class);
  }

  @Override
  @Nullable
  public JsonnetObjinside getObjinside() {
    return findChildByClass(JsonnetObjinside.class);
  }

  @Override
  @Nullable
  public JsonnetParams getParams() {
    return findChildByClass(JsonnetParams.class);
  }

  @Override
  @Nullable
  public JsonnetSelf getSelf() {
    return findChildByClass(JsonnetSelf.class);
  }

  @Override
  @Nullable
  public JsonnetTrue getTrue() {
    return findChildByClass(JsonnetTrue.class);
  }

  @Override
  @Nullable
  public JsonnetUnaryop getUnaryop() {
    return findChildByClass(JsonnetUnaryop.class);
  }

}
