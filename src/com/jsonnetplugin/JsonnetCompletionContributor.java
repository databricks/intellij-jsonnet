package com.jsonnetplugin;

import static com.jsonnetplugin.JsonnetIdentifierReference.*;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.jsonnetplugin.psi.*;
import gherkin.deps.com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonnetCompletionContributor extends CompletionContributor {
    public JsonnetCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(JsonnetTypes.IDENTIFIER).withLanguage(JsonnetLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement element = parameters.getPosition().getOriginalElement();

                        while (element != null) {
                            if (element instanceof JsonnetSelect) {
                                PsiElement resolved = getResolvedElement((JsonnetSelect) element);
                                if (resolved instanceof JsonnetBind) {
                                    JsonnetExpr expr = ((JsonnetBind) resolved).getExpr();
                                    if (expr.getExpr0().getFirstChild() instanceof JsonnetObj) {
                                        JsonnetObj obj = (JsonnetObj) expr.getExpr0().getFirstChild();
                                        addMembersFromObject(obj, resultSet);
                                    }
                                } else if (resolved instanceof JsonnetObj) {
                                    addMembersFromObject((JsonnetObj) resolved, resultSet);
                                }
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
                            }else if (element instanceof JsonnetObjinside) {
                                List<JsonnetObjlocal> locals = ((JsonnetObjinside)element).getObjlocalList();
                                for (JsonnetMember m: ((JsonnetObjinside)element).getMembers().getMemberList()){
                                    if (m.getObjlocal() != null){
                                        locals.add(m.getObjlocal());
                                    }
                                }
                                for (JsonnetObjlocal local: locals) {
                                    JsonnetBind b = local.getBind();
                                    resultSet.addElement(LookupElementBuilder.create(b.getIdentifier0().getText()));
                                }
                            }else if (element.getParent() instanceof JsonnetBind &&
                                    ((JsonnetBind)element.getParent()).getExpr() == element){
                                List<JsonnetIdentifier0> idents = findIdentifierFromParams(((JsonnetBind)element.getParent()).getParams());
                                for(JsonnetIdentifier0 ident: idents){
                                    resultSet.addElement(LookupElementBuilder.create(ident.getText()));
                                }
                            }else if (element.getParent() instanceof JsonnetField &&
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
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        if (checkIfImport(parameters.getPosition())) {
                            String text = parameters.getPosition().getText();
                            addFileCompletions(parameters.getOriginalFile(), text, resultSet);
                        }
                    }
                }
        );
    }

    private static void addMembersFromObject(JsonnetObj obj, CompletionResultSet resultSet) {
        List<JsonnetMember> memberList = obj.getObjinside().getMemberList();
        for (JsonnetMember member : memberList) {
            if (member.getField() != null) {
                String fieldName = member.getField().getFieldname().getIdentifier0().getText();
                resultSet.addElement(LookupElementBuilder.create(fieldName));
            }
        }
    }

    /**
     * x.y.z.(dummy token)
     */
    private static PsiElement getResolvedElement(JsonnetSelect elem) {
        JsonnetExpr expr = (JsonnetExpr) elem.getParent();
        if (expr.getSelectList().size() == 1) {
            return resolveExpr0(expr.getExpr0());
        } else {
            JsonnetSelect select = expr.getSelectList().get(expr.getSelectList().size() - 2);
            PsiElement result = select.getIdentifier0();
            if (result == null) return null;
            PsiReference reference = result.getReference();
            return reference.resolve();
        }
    }

    private static PsiElement resolveExpr0(JsonnetExpr0 expr0) {
        if (expr0.getText().equals("self")) {
            return findOuterObject(expr0);
        }
        PsiElement result = expr0.getIdentifier0();
        if (result == null) return null;
        PsiReference reference = result.getReference();
        return reference.resolve();
    }

    private static PsiElement findOuterObject(PsiElement elem) {
        PsiElement curr = elem;
        while (curr != null && !(curr instanceof JsonnetObj)) {
            curr = curr.getParent();
        }
        return curr;
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
            File[] files = prefixFile.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(input);
                }
            });
            for (File f: files) {
               String result = stripped + f.getName().substring(input.length());
               replaceSet.addElement(LookupElementBuilder.create(result));
            }
        }
    }
}
