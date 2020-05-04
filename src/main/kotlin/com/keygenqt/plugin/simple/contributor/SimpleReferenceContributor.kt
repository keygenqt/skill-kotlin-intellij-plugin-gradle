package com.keygenqt.plugin.simple.contributor

import com.intellij.openapi.util.*
import com.intellij.patterns.*
import com.intellij.psi.*
import com.intellij.util.*
import com.keygenqt.plugin.simple.annotator.SimpleAnnotator.Companion.SIMPLE_PREFIX_STR
import com.keygenqt.plugin.simple.annotator.SimpleAnnotator.Companion.SIMPLE_SEPARATOR_STR

class SimpleReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(element: PsiElement,
                    context: ProcessingContext): Array<PsiReference> {
                    if (element is PsiLiteralExpression) {
                        when (val value = element.value) {
                            is String -> {
                                if (value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR)) {
                                    val property =
                                        TextRange(SIMPLE_PREFIX_STR.length + SIMPLE_SEPARATOR_STR.length + 1,
                                            value.length + 1)
                                    return arrayOf(
                                        SimpleReference(element, property))
                                }
                            }
                        }
                    }
                    return PsiReference.EMPTY_ARRAY
                }
            })
    }
}