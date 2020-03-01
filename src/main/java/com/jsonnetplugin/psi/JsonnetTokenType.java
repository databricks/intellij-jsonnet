package com.jsonnetplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.jsonnetplugin.JsonnetLanguage;
import org.jetbrains.annotations.*;

public class JsonnetTokenType extends IElementType {
    public JsonnetTokenType(@NotNull @NonNls String debugName) {
        super(debugName, JsonnetLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "JsonnetTokenType." + super.toString();
    }
}