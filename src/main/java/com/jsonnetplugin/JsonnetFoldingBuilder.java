package com.jsonnetplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jsonnetplugin.psi.JsonnetArr;
import com.jsonnetplugin.psi.JsonnetArrcomp;
import com.jsonnetplugin.psi.JsonnetObj;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonnetFoldingBuilder extends FoldingBuilderEx {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        Collection<PsiElement> literalExpressions = PsiTreeUtil.findChildrenOfType(root, JsonnetObj.class);
        literalExpressions.addAll(PsiTreeUtil.findChildrenOfType(root, JsonnetArr.class));
        literalExpressions.addAll(PsiTreeUtil.findChildrenOfType(root, JsonnetArrcomp.class));
        for (final PsiElement literalExpression : literalExpressions) {
            FoldingGroup group = FoldingGroup.newGroup(
                    "jsonnet-" + literalExpression.getTextRange().getStartOffset() +
                            "-" + literalExpression.getTextRange().getEndOffset()
            );
            int start = literalExpression.getTextRange().getStartOffset() + 1;
            int end = literalExpression.getTextRange().getEndOffset() - 1;
            if (end > start)
                descriptors.add(
                        new FoldingDescriptor(
                                literalExpression.getNode(),
                                new TextRange(start, end),
                                group
                        ) {
                            @Override
                            public String getPlaceholderText() {
                                return "...";
                            }
                        }
                );
        }
        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}
