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

package com.keygenqt.plugin.simple.formatting

import com.intellij.formatting.*
import com.intellij.formatting.WrapType.*
import com.intellij.lang.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.psi.codeStyle.*
import com.keygenqt.plugin.simple.language.*
import org.intellij.sdk.language.psi.*

class SimpleFormattingModelBuilder : FormattingModelBuilder {

    override fun createModel(element: PsiElement, settings: CodeStyleSettings): FormattingModel {
        return FormattingModelProvider.createFormattingModelForPsiFile(element.containingFile,
            SimpleBlock(element.node, Wrap.createWrap(NONE, false), Alignment.createAlignment(),
                createSpaceBuilder(settings)), settings)
    }

    override fun getRangeAffectingIndent(file: PsiFile, offset: Int, elementAtOffset: ASTNode): TextRange? {
        return null
    }

    companion object {
        private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
            return SpacingBuilder(settings, SimpleLanguage.INSTANCE)
                .around(SimpleTypes.SEPARATOR)
                .spaceIf(settings.getCommonSettings(SimpleLanguage.INSTANCE.id).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(SimpleTypes.PROPERTY)
                .none()
        }
    }
}