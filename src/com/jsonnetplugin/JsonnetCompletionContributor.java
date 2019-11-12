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
import java.util.List;

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
                                JsonnetObj resolved = resolveExprToObj((JsonnetExpr) element.getParent());
                                if (resolved != null) {
                                    addMembersFromObject(resolved, resultSet);
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
                                List<JsonnetObjlocal> locals = ((JsonnetObjinside) element).getObjlocalList();
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

    private static void addMembersFromObject(JsonnetObj obj, CompletionResultSet resultSet) {
        if (obj.getObjinside() == null || obj.getObjinside().getMembers() == null) return;

        List<JsonnetMember> memberList = obj.getObjinside().getMembers().getMemberList();
        for (JsonnetMember member : memberList) {
            if (member.getField() != null && member.getField().getFieldname().getIdentifier0() != null) {
                String fieldName = member.getField().getFieldname().getIdentifier0().getText();
                resultSet.addElement(LookupElementBuilder.create(fieldName));
            }
        }
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
