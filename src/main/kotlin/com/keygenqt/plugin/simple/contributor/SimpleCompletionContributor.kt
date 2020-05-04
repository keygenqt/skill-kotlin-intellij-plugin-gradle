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