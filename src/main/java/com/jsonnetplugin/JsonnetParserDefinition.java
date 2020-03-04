package com.jsonnetplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.jsonnetplugin.parser.JsonnetParser;
import com.jsonnetplugin.psi.JsonnetFile;
import com.jsonnetplugin.psi.JsonnetTypes;
import org.jetbrains.annotations.NotNull;

public class JsonnetParserDefinition implements ParserDefinition {
    public static final TokenSet COMMENTS = TokenSet.create(JsonnetTypes.LINE_COMMENT, JsonnetTypes.BLOCK_COMMENT);

    public static final IFileElementType FILE = new IFileElementType(JsonnetLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new JsonnetLexerAdapter();
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new JsonnetParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new JsonnetFile(viewProvider);
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return JsonnetTypes.Factory.createElement(node);
    }
}
