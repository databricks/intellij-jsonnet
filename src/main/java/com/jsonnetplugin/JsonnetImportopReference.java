package com.jsonnetplugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class JsonnetImportopReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public JsonnetImportopReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        VirtualFile projectRoot = ProjectUtil.guessProjectDir(getElement().getProject());
        String importText = this.getElement().getLastChild().getText();
        if (importText.startsWith("'") || importText.startsWith("\"")) {
            importText = importText.substring(1);
        }
        if (importText.endsWith("'") || importText.endsWith("\"")) {
            importText = importText.substring(0, importText.length() - 1);
        }

        // try and find the import relative to the file that is doing the importing
        VirtualFile vf = getElement()
                .getContainingFile()
                .getOriginalFile()
                .getVirtualFile()
                .getParent()
                .findFileByRelativePath(importText);

        // try and find the import relative to the workspace root
        if (vf == null && projectRoot != null) {
            vf = projectRoot.findFileByRelativePath(importText);
        }

        // try and find the import relative to bazel output (for generated files)
        if (vf == null && projectRoot != null) {
            vf = projectRoot.findFileByRelativePath("bazel-bin/" + importText);
        }

        if (vf != null) {
            PsiFile myPsiFile = PsiManager.getInstance(myElement.getProject()).findFile(vf);
            if (myPsiFile != null) return new ResolveResult[]{new PsiElementResolveResult(myPsiFile)};
            else return new ResolveResult[]{};
        } else {
            return new ResolveResult[]{};
        }

    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new LookupElement[]{};
    }
}
