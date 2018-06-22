package com.jsonnetplugin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.jsonnetplugin.psi.JsonnetImportop;
import com.jsonnetplugin.psi.JsonnetImportstrop;
import org.jetbrains.annotations.NotNull;

public class JsonnetReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        System.out.println("JsonnetReferenceContributor#registerReferenceProviders");
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(JsonnetImportop.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext
                                                                         context) {
                        return new PsiReference[]{
                                new JsonnetReference(
                                        element,
                                        element.getTextRange().shiftLeft(element.getTextOffset())
                                )
                        };
                    }
                });
    }
}