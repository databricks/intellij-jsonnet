package com.jsonnetplugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.jsonnetplugin.psi.JsonnetNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class JsonnetNamedElementImpl extends ASTWrapperPsiElement implements JsonnetNamedElement {
    public JsonnetNamedElementImpl(@NotNull ASTNode node) {
        super(node);
        System.out.println("JsonnetNamedElementImpl.<init>");
    }

    @Override
    public PsiReference getReference() {
        System.out.println("JsonnetNamedElementImpl.getReference " + super.getReference());
        return super.getReference();
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        PsiReference[] x = ReferenceProvidersRegistry.getReferencesFromProviders(this);
        System.out.println("JsonnetNamedElementImpl.getReferences " + x.length);

        return x;
    }
}