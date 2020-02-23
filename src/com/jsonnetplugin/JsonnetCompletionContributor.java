package com.jsonnetplugin;

import static com.jsonnetplugin.JsonnetIdentifierReference.*;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.jsonnetplugin.psi.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonnetCompletionContributor extends CompletionContributor {
    public JsonnetCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(JsonnetTypes.IDENTIFIER).withLanguage(JsonnetLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement element = parameters.getPosition().getOriginalElement();

                        while (element != null) {
                            if (element instanceof JsonnetSelect) {
                                JsonnetObjinside[] resolved = resolveExprToObj((JsonnetExpr) element.getParent());
                                if (resolved != null) {
                                    for(JsonnetObjinside r: resolved){
                                        addMembersFromObject(r, resultSet);
                                    }
                                }

                                // Do not show suggestions from outer space if the element
                                // before the dot can be resolved. We are only interested in the fields.
                                return;
                            } else if (element instanceof JsonnetOuterlocal) {
                                List<JsonnetBind> binds = JsonnetIdentifierReference.findBindInOuterLocal((JsonnetOuterlocal) element);
                                for (JsonnetBind b: binds) {
                                    resultSet.addElement(LookupElementBuilder.create(b.getIdentifier0().getText()));
                                }
                            } else if (element instanceof JsonnetExpr0 && isFunctionExpr((JsonnetExpr0) element)) {
                                List<JsonnetIdentifier0> identifiers = JsonnetIdentifierReference.findIdentifierFromFunctionExpr0((JsonnetExpr0) element);
                                for (JsonnetIdentifier0 i: identifiers) {
                                    resultSet.addElement(LookupElementBuilder.create(i.getText()));
                                }
                            } else if (element instanceof JsonnetObjinside) {

                                List<JsonnetObjlocal> locals = new ArrayList<>(((JsonnetObjinside) element).getObjlocalList());

                                JsonnetMembers members = ((JsonnetObjinside) element).getMembers();
                                if (members != null) {
                                    for (JsonnetMember m: members.getMemberList()){
                                        if (m.getObjlocal() != null){
                                            locals.add(m.getObjlocal());
                                        }
                                    }
                                }
                                for (JsonnetObjlocal local: locals) {
                                    JsonnetBind b = local.getBind();
                                    resultSet.addElement(LookupElementBuilder.create(b.getIdentifier0().getText()));
                                }
                            } else if (element.getParent() instanceof JsonnetBind &&
                                    ((JsonnetBind)element.getParent()).getExpr() == element){
                                List<JsonnetIdentifier0> idents = findIdentifierFromParams(((JsonnetBind)element.getParent()).getParams());
                                for(JsonnetIdentifier0 ident: idents){
                                    resultSet.addElement(LookupElementBuilder.create(ident.getText()));
                                }
                            } else if (element.getParent() instanceof JsonnetField &&
                                    ((JsonnetField)element.getParent()).getExpr() == element){
                                List<JsonnetIdentifier0> idents = findIdentifierFromParams(((JsonnetField)element.getParent()).getParams());
                                for(JsonnetIdentifier0 ident: idents){
                                    resultSet.addElement(LookupElementBuilder.create(ident.getText()));
                                }
                            }
                            element = element.getParent();
                        }

                        resultSet.addElement(LookupElementBuilder.create("null"));
                        resultSet.addElement(LookupElementBuilder.create("true"));
                        resultSet.addElement(LookupElementBuilder.create("false"));
                        resultSet.addElement(LookupElementBuilder.create("local"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(JsonnetTypes.DOUBLE_QUOTED_STRING).withLanguage(JsonnetLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        if (checkIfImport(parameters.getPosition())) {
                            String text = parameters.getPosition().getText();
                            addFileCompletions(parameters.getOriginalFile(), text, resultSet);
                        }
                    }
                }
        );
    }

    private static void addMembersFromObject(JsonnetObjinside obj, CompletionResultSet resultSet) {
        if (obj == null || obj.getMembers() == null) return;

        List<JsonnetMember> memberList = obj.getMembers().getMemberList();
        for (JsonnetMember member : memberList) {
            if (member.getField() != null && member.getField().getFieldname().getIdentifier0() != null) {
                String fieldName = member.getField().getFieldname().getIdentifier0().getText();
                resultSet.addElement(LookupElementBuilder.create(fieldName));
            }
        }
    }

    private static JsonnetObjinside[] resolveExprToObj(JsonnetExpr expr) {
        return resolveExprToObj(expr, new ArrayList<>());
    }

    /**
     * Resolves an expression of the form x.y.z.(dummy token) to an instance of JsonnetObj
     * if possible, otherwise returns null.
     * To avoid infinite loops, we keep track of the list of expressions visited along this
     * call chain.git p
     */
    private static JsonnetObjinside[] resolveExprToObj(JsonnetExpr expr, List<JsonnetExpr> visited) {
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

    static JsonnetObjinside[] resolveExprToObj(JsonnetExpr expr, List<JsonnetExpr> visited, List<JsonnetIdentifier0> selectList) {
        JsonnetObjinside[] curr = resolveExprLhsToObj(expr, visited, selectList);

        List<JsonnetObjinside> extended = new java.util.ArrayList<>();

        if (curr != null) {
            for(JsonnetObjinside i: curr) {
                extended.add(i);
            }
        }
        for (JsonnetObjextend x: expr.getObjextendList()){
            extended.add(x.getObjinside());
        }
        for (JsonnetBinsuffix x: expr.getBinsuffixList()){
            JsonnetObjinside[] rhs = resolveExprToObj(x.getExpr(), visited);
            if (rhs != null){
                for(JsonnetObjinside i: rhs) {
                    extended.add(i);
                }
            }
        }

        if (extended.size() == 0) return null;
        else{
            JsonnetObjinside[] res = new JsonnetObjinside[extended.size()];
            extended.toArray(res);
            return res;
        }
    }


    static JsonnetObjinside[] resolveExprLhsToObj(JsonnetExpr expr, List<JsonnetExpr> visited, List<JsonnetIdentifier0> selectList) {
        JsonnetExpr0 first = expr.getExpr0();
        JsonnetObjinside[] curr = resolveExpr0ToObj(first, visited);
        for (JsonnetIdentifier0 select : selectList) {
            if (curr == null) return null;

            List<JsonnetExpr> fieldValues = Arrays.stream(curr)
                    .map(c -> getField(c, select.getText()))
                    .filter(c -> c != null)
                    .collect(Collectors.toList());

            if (fieldValues.isEmpty()) return null;

            List<JsonnetObjinside> resolvedList = fieldValues.stream()
                    .map(f -> resolveExprToObj(f, visited))
                    .filter(c -> c != null)
                    .flatMap(c -> Arrays.stream(c))
                    .collect(Collectors.toList());

            curr = new JsonnetObjinside[resolvedList.size()];
            resolvedList.toArray(curr);
        }
        return curr;
    }

    private static JsonnetObjinside[] resolveIdentifierToObj(JsonnetIdentifier0 id, List<JsonnetExpr> visited) {
        if (id.getReference() == null) return null;
        PsiElement resolved = id.getReference().resolve();
        if (resolved instanceof JsonnetBind) {
            JsonnetExpr expr = ((JsonnetBind) resolved).getExpr();
            return resolveExprToObj(expr, visited);
        }
        return null;
    }

    private static JsonnetExpr getField(JsonnetObjinside obj, String name) {
        if (obj == null || obj.getMembers() == null) return null;

        List<JsonnetMember> memberList = obj.getMembers().getMemberList();
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

    private static JsonnetObjinside[] resolveExpr0ToObj(JsonnetExpr0 expr0, List<JsonnetExpr> visited) {
        if (expr0.getExpr() != null){
            return resolveExprToObj(expr0.getExpr(), visited);
        }
        if (expr0.getOuterlocal() != null){
            return resolveExprToObj(expr0.getOuterlocal().getExpr(), visited);
        }
        if (expr0.getObj() != null && expr0.getObj().getObjinside() != null){
            return new JsonnetObjinside[]{expr0.getObj().getObjinside()};
        }
        if (expr0.getText().equals("self")) {
            return new JsonnetObjinside[]{findSelfObject(expr0)};
        }
        if (expr0.getText().equals("$")) {
            return new JsonnetObjinside[]{findOuterObject(expr0)};
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
                    JsonnetObjinside[] res = resolveExprToObj((JsonnetExpr) c, visited);
                    if (res != null) return res;
                }
            }
        }
        if (expr0.getIdentifier0() != null) {
            return resolveIdentifierToObj(expr0.getIdentifier0(), visited);
        }

        return null;
    }

    private static JsonnetObjinside findSelfObject(PsiElement elem) {
        PsiElement curr = elem;
        while (curr != null && !(curr instanceof JsonnetObj)) {
            curr = curr.getParent();
        }
        return ((JsonnetObj) curr).getObjinside();
    }

    private static JsonnetObjinside findOuterObject(PsiElement elem) {
        JsonnetObj obj = null;
        PsiElement curr = elem;
        while (curr != null) {
            if (curr instanceof JsonnetObj) {
                obj = (JsonnetObj) curr;
            }
            curr = curr.getParent();
        }
        return (obj).getObjinside();
    }

    private static boolean checkIfImport(PsiElement position) {
        return position.getPrevSibling() != null &&
          position.getPrevSibling().getPrevSibling().getNode().getElementType().equals(JsonnetTypes.IMPORT);
    }

    private static void addFileCompletions(PsiFile file, String current, CompletionResultSet set) {
        // current always begins with a "
        String cleanedCurrent = current.substring(1);
        if (cleanedCurrent.endsWith("\"")) {
           cleanedCurrent = cleanedCurrent.substring(0, cleanedCurrent.length() - 1);
        }

        if (!cleanedCurrent.endsWith(Constants.INTELLIJ_RULES)) {
            return;
        }
        Path currentPath = Paths.get(file.getContainingDirectory().getVirtualFile().getPath());
        String stripped = cleanedCurrent.replace(Constants.INTELLIJ_RULES, "");
        Path strippedPath = Paths.get(stripped);
        int strippedPathCount = strippedPath.getNameCount();

        File prefixFile;
        String input;
        if (stripped.endsWith("/")) {
            prefixFile = currentPath.resolve(Paths.get(stripped)).toFile();
            input = "";
        } else if (strippedPathCount == 1){
            prefixFile = currentPath.toFile();
            input = stripped;
        } else {
            prefixFile = currentPath.resolve(strippedPath.subpath(0, strippedPathCount - 1)).toFile();
            input = strippedPath.subpath(strippedPathCount-1, strippedPathCount).toString();
        }

        CompletionResultSet replaceSet = set.withPrefixMatcher(stripped);
        if (prefixFile.isDirectory()) {
            File[] files = prefixFile.listFiles((dir, name) -> name.startsWith(input));
            if (files != null) {
                for (File f: files) {
                    String result = stripped + f.getName().substring(input.length());
                    replaceSet.addElement(LookupElementBuilder.create(result));
                }
            }
        }
    }
}
