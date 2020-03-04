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
    }

    @Override
    public PsiReference getReference() {
        PsiReference[] referencesFromProviders = ReferenceProvidersRegistry.getReferencesFromProviders(this);
        if (referencesFromProviders.length == 1)
            return referencesFromProviders[0];
        else
            return null;
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }
}
