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

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.keygenqt.plugin.simple.file.*
import org.intellij.sdk.language.psi.*

class SimpleElementFactory {
    companion object {
        fun createProperty(project: Project?, name: String?): SimpleProperty? {
            val file = createFile(project, name)
            return file.firstChild as SimpleProperty
        }

        fun createFile(project: Project?, text: String?): SimpleFile {
            val name = "dummy.simple"
            return PsiFileFactory.getInstance(project)
                .createFileFromText(name, SimpleFileType.INSTANCE, text!!) as SimpleFile
        }

        fun createProperty(project: Project?, name: String, value: String): SimpleProperty {
            val file = createFile(project, "$name = $value")
            return file.firstChild as SimpleProperty
        }

        fun createCRLF(project: Project?): PsiElement {
            val file = createFile(project, "\n")
            return file.firstChild
        }
    }
}