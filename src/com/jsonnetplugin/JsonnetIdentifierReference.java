package com.jsonnetplugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.jsonnetplugin.psi.JsonnetBind;
import com.jsonnetplugin.psi.JsonnetExpr;
import com.jsonnetplugin.psi.JsonnetExpr0;
import com.jsonnetplugin.psi.JsonnetOuterlocal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class JsonnetIdentifierReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public JsonnetIdentifierReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<ResolveResult> results = new ArrayList<>();
        String identifier = this.getElement().getText();
        PsiElement element = this.getElement();
        while (element != null) {
            if (element instanceof JsonnetOuterlocal) {
                List<JsonnetBind> binds = findBindInOuterLocal((JsonnetOuterlocal) element);
                for (JsonnetBind b: binds) {
                    if (identifier.equals(findIdentifierFromBind(b))) {
                        results.add(new PsiElementResolveResult(b));
                        return results.toArray(new ResolveResult[results.size()]);
                    }
                }
            }
            element = element.getParent();
        }
        return results.toArray(new ResolveResult[results.size()]);
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

    public static List<JsonnetBind> findBindInOuterLocal(JsonnetOuterlocal element) {
        List<JsonnetBind> ret = new ArrayList<>();
        if (element instanceof JsonnetExpr) {
            return new ArrayList<>();
        }
        for (PsiElement child: element.getChildren()) {
            if (child instanceof JsonnetBind) {
                ret.add((JsonnetBind) child);
            }
        }
        return ret;
    }

    public static String findIdentifierFromBind(JsonnetBind element) {
        return element.getFirstChild().getText();
    }
}
