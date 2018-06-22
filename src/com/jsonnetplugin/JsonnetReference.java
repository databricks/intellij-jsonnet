package com.jsonnetplugin;

import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class JsonnetReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public JsonnetReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        System.out.println("JsonnetReference");
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        System.out.println("JsonnetReference#multiResolve");
        String importText = this.getElement().getLastChild().getText();
        if (importText.startsWith("\"")) importText = importText.substring(1);
        if (importText.endsWith("\"")) importText = importText.substring(0, importText.length() - 1);

        VirtualFile vf = getElement()
                .getContainingFile()
                .getVirtualFile()
                .getParent()
                .findFileByRelativePath(importText);

        if (vf != null){
            PsiFile myPsiFile = PsiManager.getInstance(myElement.getProject()).findFile(vf);
            if (myPsiFile != null) return new ResolveResult[]{new PsiElementResolveResult(myPsiFile)};
            else return new ResolveResult[]{};
        }else{
            return new ResolveResult[]{};

        }

    }

    @Nullable
    @Override
    public PsiElement resolve() {
        System.out.println("JsonnetReference#resolve");
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        System.out.println("JsonnetReference#getVariants");
//        Project project = myElement.getProject();
//        List<JsonnetProperty> properties = JsonnetUtil.findProperties(project);
//        List<LookupElement> variants = new ArrayList<LookupElement>();
//        for (final JsonnetProperty property : properties) {
//            if (property.getKey() != null && property.getKey().length() > 0) {
//                variants.add(LookupElementBuilder.create(property).
//                        withIcon(JsonnetIcons.FILE).
//                        withTypeText(property.getContainingFile().getName())
//                );
//            }
//        }
//        return variants.toArray();
        return new LookupElement[]{};
    }
}
