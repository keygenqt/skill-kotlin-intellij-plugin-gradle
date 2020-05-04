/*
 * Copyright 2020 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.keygenqt.plugin.simple.folding

import com.intellij.lang.*
import com.intellij.lang.folding.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.keygenqt.plugin.simple.annotator.*
import com.keygenqt.plugin.simple.util.SimpleUtil.Companion.findProperties
import java.util.*

class SimpleFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, uick: Boolean): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(SimpleAnnotator.SIMPLE_PREFIX_STR)
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        val literalExpressions = PsiTreeUtil.findChildrenOfType(root, PsiLiteralExpression::class.java)

        for (literalExpression in literalExpressions) {
            when (val value = literalExpression.value) {
                is String -> {
                    if (value.startsWith(SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)) {
                        val project = literalExpression.project
                        val key = value.substring(
                            SimpleAnnotator.SIMPLE_PREFIX_STR.length + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length)
                        val properties = findProperties(project, key)
                        if (properties.isNotEmpty()) {
                            descriptors.add(FoldingDescriptor(literalExpression.node,
                                TextRange(literalExpression.textRange.startOffset + 1,
                                    literalExpression.textRange.endOffset - 1), group))
                        }
                    }
                }
            }
        }
        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        val retTxt = "..."
        if (node.psi is PsiLiteralExpression) {
            val nodeElement = node.psi
            if (nodeElement is PsiLiteralExpression) {
                val value = nodeElement.value
                if (value is String) {
                    val key = value.substring(
                        SimpleAnnotator.SIMPLE_PREFIX_STR.length + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length)
                    findProperties(nodeElement.project, key)[0].value?.let {
                        return it.replace("\n".toRegex(), "\\n")?.replace("\"".toRegex(), "\\\\\"") ?: retTxt
                    }
                }
            }
        }
        return retTxt
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return true
    }
}