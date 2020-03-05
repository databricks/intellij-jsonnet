package com.jsonnetplugin;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.jsonnetplugin.psi.JsonnetFieldname;
import com.jsonnetplugin.psi.JsonnetIdentifier0;
import com.jsonnetplugin.psi.JsonnetSelect;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class JsonnetAnnotator implements Annotator {
    final TextAttributesKey MEMBER = createTextAttributesKey("JSONNET_MEMBER", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (psiElement instanceof JsonnetIdentifier0 && psiElement.getParent() instanceof JsonnetFieldname) {
            annotationHolder.createInfoAnnotation(psiElement, "").setTextAttributes(MEMBER);
        }
        if (psiElement instanceof JsonnetIdentifier0 && psiElement.getParent() instanceof JsonnetSelect) {
            annotationHolder.createInfoAnnotation(psiElement, "").setTextAttributes(MEMBER);
        }
    }
}
