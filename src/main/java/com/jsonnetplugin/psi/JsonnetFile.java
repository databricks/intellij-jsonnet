package com.jsonnetplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.jsonnetplugin.JsonnetFileType;
import com.jsonnetplugin.JsonnetLanguage;
import org.jetbrains.annotations.NotNull;

public class JsonnetFile extends PsiFileBase {
    public JsonnetFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, JsonnetLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return JsonnetFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Jsonnet file";
    }
}
