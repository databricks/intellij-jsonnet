package com.jsonnetplugin;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jsonnetplugin.psi.JsonnetTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonnetBraceMatcher implements PairedBraceMatcher {

    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(JsonnetTypes.L_BRACKET, JsonnetTypes.R_BRACKET, false),
            new BracePair(JsonnetTypes.L_PAREN, JsonnetTypes.R_PAREN, false),
            new BracePair(JsonnetTypes.L_CURLY, JsonnetTypes.R_CURLY, true),
    };

    @NotNull
    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType type, @Nullable IElementType tokenType) {
        return false;
    }

    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
