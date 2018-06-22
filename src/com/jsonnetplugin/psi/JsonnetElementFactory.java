package com.jsonnetplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.jsonnetplugin.JsonnetFileType;

public class JsonnetElementFactory {
    public static JsonnetFile createFile(Project project, String text) {
        String name = "dummy.jsonnet";
        return (JsonnetFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, JsonnetFileType.INSTANCE, text);
    }
}