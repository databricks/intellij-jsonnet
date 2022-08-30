package com.jsonnetplugin.spellchecker;

import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.jsonnetplugin.psi.JsonnetTypes;
import org.jetbrains.annotations.NotNull;

public class JsonnetSpellcheckingStrategy extends SpellcheckingStrategy {
    private final JsonnetIdentifierTokenizer myJsonnetIdentifierTokenizer = new JsonnetIdentifierTokenizer();

    @NotNull
    @Override
    public Tokenizer getTokenizer(PsiElement element) {
        if (element.getNode().getElementType() == JsonnetTypes.IDENTIFIER) {
            return myJsonnetIdentifierTokenizer;
        }
        return super.getTokenizer(element);
    }
}
