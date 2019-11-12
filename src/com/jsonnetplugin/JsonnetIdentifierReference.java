package com.jsonnetplugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jsonnetplugin.psi.*;
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
            if (element instanceof JsonnetSelect) {
                List<JsonnetIdentifier0> selectList = new ArrayList<>();
                for (JsonnetSelect select : ((JsonnetExpr)element.getParent()).getSelectList()) {
                    if (select == element) {
                        break;
                    }
                    selectList.add(select.getIdentifier0());
                }
                JsonnetObj obj = JsonnetCompletionContributor.resolveExprToObj(
                        (JsonnetExpr)element.getParent(),
                        new ArrayList<>(),
                        selectList
                );
                if (obj != null){

                    if (obj.getObjinside() != null && obj.getObjinside().getMembers() != null){
                        String lastSelectText = ((JsonnetSelect) element).getIdentifier0().getText();
                        JsonnetMembers members = obj.getObjinside().getMembers();
                        for(JsonnetMember m: members.getMemberList()){
                            if (m.getField() != null){
                                JsonnetIdentifier0 ident = m.getField().getFieldname().getIdentifier0();
                                if (ident != null && ident.getText().equals(lastSelectText)){
                                    results.add(new PsiElementResolveResult(m.getField().getFieldname().getIdentifier0()));
                                    return results.toArray(new ResolveResult[results.size()]);
                                }
                            }
                        }
                    }

                    results.add(new PsiElementResolveResult(obj));
                    return results.toArray(new ResolveResult[results.size()]);
                }

            } else if (element instanceof JsonnetOuterlocal) {
                List<JsonnetBind> binds = findBindInOuterLocal((JsonnetOuterlocal) element);
                for (JsonnetBind b: binds) {
                    if (identifier.equals(findIdentifierFromBind(b))) {
                        results.add(new PsiElementResolveResult(b));
                        return results.toArray(new ResolveResult[results.size()]);
                    }
                }
            } else if (element instanceof JsonnetExpr0 && isFunctionExpr((JsonnetExpr0) element)) {
                List<JsonnetIdentifier0> identifiers = findIdentifierFromFunctionExpr0((JsonnetExpr0) element);
                for (JsonnetIdentifier0 i: identifiers) {
                    if (identifier.equals(i.getText())) {
                        results.add(new PsiElementResolveResult(i));
                        return results.toArray(new ResolveResult[results.size()]);
                    }
                }
            }else if (element instanceof JsonnetObjinside) {

                List<JsonnetObjlocal> locals = new ArrayList<>(((JsonnetObjinside)element).getObjlocalList());
                JsonnetMembers members = ((JsonnetObjinside)element).getMembers();
                if (members != null){
                    for (JsonnetMember m: members.getMemberList()){
                        if (m.getObjlocal() != null){
                            locals.add(m.getObjlocal());
                        }
                    }
                }
                for (JsonnetObjlocal local: locals) {
                    JsonnetBind b = local.getBind();
                    if (identifier.equals(findIdentifierFromBind(b))) {
                        results.add(new PsiElementResolveResult(b));
                        return results.toArray(new ResolveResult[results.size()]);
                    }
                }
            }else if (element.getParent() instanceof JsonnetBind &&
                    ((JsonnetBind)element.getParent()).getExpr() == element){
                List<JsonnetIdentifier0> idents = new ArrayList<>(findIdentifierFromParams(((JsonnetBind)element.getParent()).getParams()));
                for(JsonnetIdentifier0 ident: idents){
                    if (identifier.equals(ident.getText())) {
                        results.add(new PsiElementResolveResult(ident));
                        return results.toArray(new ResolveResult[results.size()]);
                    }
                }
            }else if (element.getParent() instanceof JsonnetField &&
                    ((JsonnetField)element.getParent()).getExpr() == element){
                List<JsonnetIdentifier0> idents = new ArrayList<>(findIdentifierFromParams(((JsonnetField)element.getParent()).getParams()));
                for(JsonnetIdentifier0 ident: idents){
                    if (identifier.equals(ident.getText())) {
                        results.add(new PsiElementResolveResult(ident));
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

    public static List<JsonnetIdentifier0> findIdentifierFromFunctionExpr0(JsonnetExpr0 element) {

        PsiElement params = null;
        for (PsiElement c: element.getChildren()) {
            if (c instanceof JsonnetParams) params = c;
        }

        return findIdentifierFromParams((JsonnetParams)params);
    }

    public static List<JsonnetIdentifier0> findIdentifierFromParams(JsonnetParams params){
        List<JsonnetIdentifier0> ret = new ArrayList<>();
        if (params == null) return ret;
        for (PsiElement child: params.getChildren()) {
            if (child instanceof JsonnetParam) {
                PsiElement identifier = child.getFirstChild();
                if (!(identifier instanceof JsonnetIdentifier0)) continue;
                ret.add((JsonnetIdentifier0) identifier);
            }
        }
        return ret;
    }
    public static boolean isFunctionExpr(JsonnetExpr0 element) {
        if (element.getFirstChild() instanceof LeafPsiElement) {
            return ((LeafPsiElement) element.getFirstChild()).getElementType().equals(JsonnetTypes.FUNCTION);
        }
        return false;
    }

}
