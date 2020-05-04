package com.keygenqt.plugin.simple.contributor

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.completion.CompletionType.*
import com.intellij.codeInsight.lookup.*
import com.intellij.patterns.*
import com.intellij.util.*
import com.keygenqt.plugin.simple.language.*
import org.intellij.sdk.language.psi.*

class SimpleCompletionContributor : CompletionContributor() {
    init {
        extend(BASIC, PlatformPatterns.psiElement(SimpleTypes.VALUE).withLanguage(SimpleLanguage.INSTANCE),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet) {
                    resultSet.addElement(LookupElementBuilder.create("Hello"))
                }
            }
        )
    }
}