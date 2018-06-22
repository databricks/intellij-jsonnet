package com.jsonnetplugin;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.jsonnetplugin.psi.JsonnetIdentifier0;
import com.jsonnetplugin.psi.JsonnetImportop;
import com.jsonnetplugin.psi.JsonnetImportstrop;
import org.jetbrains.annotations.NotNull;

public class JsonnetReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(JsonnetImportop.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext
                                                                         context) {
                        return new PsiReference[]{
                                new JsonnetImportopReference(
                                        element,
                                        element.getTextRange().shiftRight(-element.getTextOffset())
                                )
                        };
                    }
                }
        );
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(JsonnetImportstrop.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext
                                                                         context) {
                        return new PsiReference[]{
                                new JsonnetImportopReference(
                                        element,
                                        element.getTextRange().shiftRight(-element.getTextOffset())
                                )
                        };
                    }
                }
        );
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(JsonnetIdentifier0.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext
                                                                         context) {
                        return new PsiReference[]{
                                new JsonnetIdentifierReference(
                                        element,
                                        element.getTextRange().shiftRight(-element.getTextOffset())
                                )
                        };
                    }
                }
        );
    }
}