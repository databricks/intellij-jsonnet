package com.jsonnetplugin;

import com.intellij.lang.Language;

public class JsonnetLanguage extends Language {
    public static final JsonnetLanguage INSTANCE = new JsonnetLanguage();

    private JsonnetLanguage() {
        super("Jsonnet");
    }
}
