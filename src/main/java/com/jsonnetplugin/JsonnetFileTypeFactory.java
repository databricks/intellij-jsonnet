package com.jsonnetplugin;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class JsonnetFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(JsonnetFileType.INSTANCE, "jsonnet");
        fileTypeConsumer.consume(JsonnetFileType.INSTANCE, "libsonnet");
        fileTypeConsumer.consume(JsonnetFileType.INSTANCE, new FileNameMatcher() {
            @Override
            public boolean accept(@NotNull String fileName) {
                return fileName.endsWith(".jsonnet.TEMPLATE");
            }

            @NotNull
            @Override
            public String getPresentableString() {
                return ".jsonnet.TEMPLATE";
            }
        });
    }
}
