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

package com.keygenqt.plugin.simple.annotator

import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.keygenqt.plugin.simple.syntax.*
import com.keygenqt.plugin.simple.util.SimpleUtil.Companion.findProperties

class SimpleAnnotator : Annotator {

    companion object {
        const val SIMPLE_PREFIX_STR = "simple"
        const val SIMPLE_SEPARATOR_STR = ":"
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiLiteralExpression) {
            when (val value = element.value) {
                is String -> {
                    if (!value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR)) return

                    val prefixRange = TextRange.from(element.textRange.startOffset, SIMPLE_PREFIX_STR.length + 1)
                    val separatorRange = TextRange.from(prefixRange.endOffset, SIMPLE_SEPARATOR_STR.length)
                    val keyRange = TextRange(separatorRange.endOffset, element.textRange.endOffset - 1)

                    val possibleProperties = value.substring(SIMPLE_PREFIX_STR.length + SIMPLE_SEPARATOR_STR.length)
                    val project = element.getProject()
                    val properties = findProperties(project, possibleProperties)

                    val keyAnnotation = holder.createInfoAnnotation(prefixRange, null)
                    keyAnnotation.textAttributes = DefaultLanguageHighlighterColors.KEYWORD

                    val separatorAnnotation = holder.createInfoAnnotation(separatorRange, null)
                    separatorAnnotation.textAttributes = SimpleSyntaxHighlighter.SEPARATOR

                    if (properties.isEmpty()) {
                        val badProperty = holder.createErrorAnnotation(keyRange, "Unresolved property")
                        badProperty.textAttributes = SimpleSyntaxHighlighter.BAD_CHARACTER
                        badProperty.registerFix(SimpleCreatePropertyQuickFix(possibleProperties))
                    } else {
                        val annotation = holder.createInfoAnnotation(keyRange, null)
                        annotation.textAttributes = SimpleSyntaxHighlighter.VALUE
                    }
                }
            }
        }
    }
}