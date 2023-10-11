package com.jsonnetplugin.spellchecker;

import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.IdentifierSplitter;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.jsonnetplugin.psi.JsonnetNamedElement;
import com.jsonnetplugin.psi.JsonnetTypes;
import org.jetbrains.annotations.NotNull;

public class JsonnetIdentifierTokenizer extends Tokenizer<PsiElement> {
    @Override
    public void tokenize(@NotNull PsiElement element, TokenConsumer consumer) {
        if (element.getNode().getElementType() == JsonnetTypes.IDENTIFIER) {
            consumer.consumeToken(element, IdentifierSplitter.getInstance());
        }
    }
}
