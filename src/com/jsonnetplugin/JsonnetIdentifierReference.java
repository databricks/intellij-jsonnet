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
                    selectList.add(select.getIdentifier0());
                    if (select == element) {
                        break;
                    }
                }
                JsonnetObj obj = resolveExprToObj(
                        (JsonnetExpr)element.getParent(),
                        new ArrayList<>(),
                        selectList
                );
                if (obj != null){
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
                for (JsonnetMember m: ((JsonnetObjinside)element).getMembers().getMemberList()){
                    if (m.getObjlocal() != null){
                        locals.add(m.getObjlocal());
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



    static JsonnetObj resolveExprToObj(JsonnetExpr expr) {
        return resolveExprToObj(expr, new ArrayList<>());
    }

    /**
     * Resolves an expression of the form x.y.z.(dummy token) to an instance of JsonnetObj
     * if possible, otherwise returns null.
     * To avoid infinite loops, we keep track of the list of expressions visited along this
     * call chain.git p
     */
    private static JsonnetObj resolveExprToObj(JsonnetExpr expr, List<JsonnetExpr> visited) {
        if (visited.contains(expr)) return null; // In the future we can give a warning here
        visited.add(expr);

        try {

            List<JsonnetIdentifier0> selectList = new ArrayList<>();
            for (JsonnetSelect select : expr.getSelectList()) {
                if (!select.getIdentifier0().getText().endsWith(Constants.INTELLIJ_RULES.trim())) {
                    selectList.add(select.getIdentifier0());
                }else{
                    break;
                }
            }
            return resolveExprToObj(expr, visited, selectList);
        }finally{
            visited.remove(expr);
        }
    }
    private static JsonnetObj resolveExprToObj(JsonnetExpr expr, List<JsonnetExpr> visited, List<JsonnetIdentifier0> selectList) {
        JsonnetExpr0 first = expr.getExpr0();
        JsonnetObj curr = resolveExpr0ToObj(first, visited);
        for (JsonnetIdentifier0 select : selectList) {
            if (curr == null) return null;

            JsonnetExpr fieldValue = getField(curr, select.getText());
            if (fieldValue == null) return null;

            curr = resolveExprToObj(fieldValue, visited);
        }

        return curr;
    }

    private static JsonnetObj resolveIdentifierToObj(JsonnetIdentifier0 id, List<JsonnetExpr> visited) {
        if (id.getReference() == null) return null;
        PsiElement resolved = id.getReference().resolve();
        if (resolved instanceof JsonnetBind) {
            JsonnetExpr expr = ((JsonnetBind) resolved).getExpr();
            return resolveExprToObj(expr, visited);
        }
        return null;
    }

    private static JsonnetExpr getField(JsonnetObj obj, String name) {
        if (obj.getObjinside() == null || obj.getObjinside().getMembers() == null) return null;

        List<JsonnetMember> memberList = obj.getObjinside().getMembers().getMemberList();
        for (JsonnetMember member : memberList) {
            if (member.getField() != null && member.getField().getFieldname().getIdentifier0() != null) {
                String fieldName = member.getField().getFieldname().getIdentifier0().getText();
                if (fieldName.equals(name)) {
                    return member.getField().getExpr();
                }
            }
        }
        return null;
    }

    private static JsonnetObj resolveExpr0ToObj(JsonnetExpr0 expr0, List<JsonnetExpr> visited) {
        if (expr0.getExpr() != null){
            return resolveExprToObj(expr0.getExpr(), visited);
        }
        if (expr0.getOuterlocal() != null){
            return resolveExprToObj(expr0.getOuterlocal().getExpr(), visited);
        }
        if (expr0.getObj() != null){
            return expr0.getObj();
        }
        if (expr0.getText().equals("self")) {
            return findSelfObject(expr0);
        }
        if (expr0.getText().equals("$")) {
            return findOuterObject(expr0);
        }
        if (expr0.getImportop() != null) {
            JsonnetImportop importop = expr0.getImportop();
            if (importop.getReference() == null) {
                return null;
            }
            PsiFile file = (PsiFile) importop.getReference().resolve();
            if (file == null) { // The imported file does not exist
                return null;
            }

            for(PsiElement c: file.getChildren()){
                // Apparently children can be line comments and other unwanted rubbish
                if (c instanceof JsonnetExpr) {
                    JsonnetObj res = resolveExprToObj((JsonnetExpr) c, visited);
                    if (res != null) return res;
                }
            }
        }
        if (expr0.getIdentifier0() != null) {
            return resolveIdentifierToObj(expr0.getIdentifier0(), visited);
        }

        return null;
    }

    private static JsonnetObj findSelfObject(PsiElement elem) {
        PsiElement curr = elem;
        while (curr != null && !(curr instanceof JsonnetObj)) {
            curr = curr.getParent();
        }
        return (JsonnetObj) curr;
    }

    private static JsonnetObj findOuterObject(PsiElement elem) {
        JsonnetObj obj = null;
        PsiElement curr = elem;
        while (curr != null) {
            if (curr instanceof JsonnetObj) {
                obj = (JsonnetObj) curr;
            }
            curr = curr.getParent();
        }
        return obj;
    }
}
