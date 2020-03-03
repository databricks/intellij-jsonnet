package com.jsonnetplugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.jsonnetplugin.psi.JsonnetTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class JsonnetSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("JSONNET_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    public static final TextAttributesKey KEY =
            createTextAttributesKey("JSONNET_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("JSONNET_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("JSONNET_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("JSONNET_NUMBER", DefaultLanguageHighlighterColors.NUMBER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{VALUE};

    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new JsonnetLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(JsonnetTypes.BLOCK_COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(JsonnetTypes.LINE_COMMENT)) {
            return COMMENT_KEYS;
        } else if (
            tokenType.equals(JsonnetTypes.TRUE) ||
            tokenType.equals(JsonnetTypes.FALSE) ||
            tokenType.equals(JsonnetTypes.NULL) ||
            tokenType.equals(JsonnetTypes.IMPORT) ||
            tokenType.equals(JsonnetTypes.IMPORTSTR) ||
            tokenType.equals(JsonnetTypes.LOCAL) ||
            tokenType.equals(JsonnetTypes.FUNCTION) ||
            tokenType.equals(JsonnetTypes.IN) ||
            tokenType.equals(JsonnetTypes.IF) ||
            tokenType.equals(JsonnetTypes.THEN) ||
            tokenType.equals(JsonnetTypes.ELSE) ||
            tokenType.equals(JsonnetTypes.SUPER) ||
            tokenType.equals(JsonnetTypes.ERROR) ||
            tokenType.equals(JsonnetTypes.SELF) ||
            tokenType.equals(JsonnetTypes.FOR) ||
            tokenType.equals(JsonnetTypes.ASSERT) ||
            tokenType.equals(JsonnetTypes.DOLLAR)
        ) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(JsonnetTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if (
                tokenType.equals(JsonnetTypes.SINGLE_QUOTED_STRING) ||
                tokenType.equals(JsonnetTypes.DOUBLE_QUOTED_STRING) ||
                tokenType.equals(JsonnetTypes.VERBATIM_DOUBLE_QUOTED_STRING) ||
                tokenType.equals(JsonnetTypes.VERBATIM_SINGLE_QUOTED_STRING) ||
                tokenType.equals(JsonnetTypes.TRIPLE_BAR_QUOTED_STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}