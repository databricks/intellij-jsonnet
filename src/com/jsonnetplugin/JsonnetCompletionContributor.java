package com.jsonnetplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.jsonnetplugin.psi.JsonnetTypes;
import org.jetbrains.annotations.NotNull;

public class JsonnetCompletionContributor extends CompletionContributor {
    public JsonnetCompletionContributor() {
        System.out.println("here");
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(JsonnetTypes.DOUBLE_QUOTED_STRING).withLanguage(JsonnetLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        System.out.println("lol");
                        resultSet.addElement(LookupElementBuilder.create("a null"));
                        resultSet.addElement(LookupElementBuilder.create("null"));
                        resultSet.addElement(LookupElementBuilder.create(JsonnetTypes.TRUE.toString()));
                        resultSet.addElement(LookupElementBuilder.create(JsonnetTypes.FALSE.toString()));
                        resultSet.addElement(LookupElementBuilder.create(JsonnetTypes.LOCAL.toString()));
                        resultSet.addElement(LookupElementBuilder.create(JsonnetTypes.LOCAL.toString()));
                    }
                }
        );
    }
}
