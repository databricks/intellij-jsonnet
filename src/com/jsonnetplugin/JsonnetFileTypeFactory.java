package com.jsonnetplugin;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class JsonnetFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(JsonnetFileType.INSTANCE);
    }
}