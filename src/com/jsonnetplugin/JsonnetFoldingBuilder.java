package com.jsonnetplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.jsonnetplugin.psi.JsonnetArr;
import com.jsonnetplugin.psi.JsonnetArrcomp;
import com.jsonnetplugin.psi.JsonnetObj;
import org.jetbrains.annotations.*;

import java.util.*;

public class JsonnetFoldingBuilder extends FoldingBuilderEx {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        Collection<PsiElement> literalExpressions = PsiTreeUtil.findChildrenOfType(root, JsonnetObj.class);
        literalExpressions.addAll(PsiTreeUtil.findChildrenOfType(root, JsonnetArr.class));
        literalExpressions.addAll(PsiTreeUtil.findChildrenOfType(root, JsonnetArrcomp.class));
        System.out.println("literalExpressions.size() " + literalExpressions.size());
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
                    @Nullable
                    @Override
                    public String getPlaceholderText() {
                        return "...";
                    }
                }
            );
        }
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}
