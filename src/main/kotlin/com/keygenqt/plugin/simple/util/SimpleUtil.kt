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

package com.keygenqt.plugin.simple.util

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.psi.util.*
import com.keygenqt.plugin.simple.file.*
import org.intellij.sdk.language.psi.*
import java.util.*

class SimpleUtil {

    companion object {
        @JvmStatic
        fun findProperties(project: Project?, key: String): List<SimpleProperty> {
            val result: ArrayList<SimpleProperty> = ArrayList()
            project?.let {
                val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(it))
                for (virtualFile in virtualFiles) {
                    val simpleFile = PsiManager.getInstance(it).findFile(virtualFile)
                    if (simpleFile is SimpleFile) {
                        PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty::class.java)?.let { properties ->
                            for (property in properties) {
                                if (key == property.key) {
                                    result.add(property)
                                }
                            }
                        }
                    }
                }
            }
            return result
        }

        @JvmStatic
        fun findProperties(project: Project?): List<SimpleProperty> {
            val result: ArrayList<SimpleProperty> = ArrayList()
            project?.let {
                val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(it))
                for (virtualFile in virtualFiles) {
                    val simpleFile = PsiManager.getInstance(it).findFile(virtualFile)
                    if (simpleFile is SimpleFile) {
                        PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty::class.java)?.let { properties ->
                            Collections.addAll(result, *properties)
                        }
                    }
                }
            }
            return result
        }
    }
}