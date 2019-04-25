package com.jsonnetplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class JsonnetFileType extends LanguageFileType {
    public static final JsonnetFileType INSTANCE = new JsonnetFileType();

    private JsonnetFileType() {
        super(JsonnetLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Jsonnet file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Jsonnet language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "jsonnet";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return JsonnetIcon.FILE;
    }
}
