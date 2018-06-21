package com.jsonnetplugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class JsonnetLexerAdapter extends FlexAdapter {
    public JsonnetLexerAdapter() {
        super(new JsonnetLexer((Reader) null));
    }
}
