package com.jsonnetplugin;

import com.intellij.lexer.FlexAdapter;

public class JsonnetLexerAdapter extends FlexAdapter {
    public JsonnetLexerAdapter() {
        super(new JsonnetLexer(null));
    }
}
