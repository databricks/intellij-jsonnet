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

public class JsonnetMemberImpl extends ASTWrapperPsiElement implements JsonnetMember {

  public JsonnetMemberImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsonnetVisitor visitor) {
    visitor.visitMember(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsonnetVisitor) accept((JsonnetVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsonnetAssert getAssert() {
    return findChildByClass(JsonnetAssert.class);
  }

  @Override
  @Nullable
  public JsonnetField getField() {
    return findChildByClass(JsonnetField.class);
  }

  @Override
  @Nullable
  public JsonnetObjlocal getObjlocal() {
    return findChildByClass(JsonnetObjlocal.class);
  }

}
