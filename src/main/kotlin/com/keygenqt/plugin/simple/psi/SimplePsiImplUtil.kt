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

package com.keygenqt.plugin.simple.psi

import com.intellij.lang.*
import com.intellij.navigation.*
import com.intellij.psi.*
import com.keygenqt.plugin.simple.contributor.*
import com.keygenqt.plugin.simple.language.*
import org.intellij.sdk.language.psi.*
import javax.swing.*

class SimplePsiImplUtil {
    companion object {
        @JvmStatic
        fun getKey(element: SimpleProperty): String? {
            val keyNode = element.node.findChildByType(SimpleTypes.KEY)
            return keyNode?.text?.replace("\\\\ ".toRegex(), " ")
        }

        @JvmStatic
        fun getValue(element: SimpleProperty): String? {
            val valueNode = element.node.findChildByType(SimpleTypes.VALUE)
            return valueNode?.text
        }

        @JvmStatic
        fun getName(element: SimpleProperty?): String? {
            return getKey(element!!)
        }

        @JvmStatic
        fun setName(element: SimpleProperty, newName: String?): PsiElement? {
            element.node.findChildByType(SimpleTypes.KEY)?.let {
                val property = SimpleElementFactory.createProperty(element.project, newName)
                val newKeyNode: ASTNode = property.firstChild.node
                element.node.replaceChild(it, newKeyNode)
            }
            return element
        }

        @JvmStatic
        fun getNameIdentifier(element: SimpleProperty): PsiElement? {
            element.node.findChildByType(SimpleTypes.KEY)?.let {
                return it.psi
            } ?: run {
                return null
            }
        }

        @JvmStatic
        fun getPresentation(element: SimpleProperty): ItemPresentation? {
            return object : ItemPresentation {
                override fun getPresentableText(): String? {
                    return element.key
                }

                override fun getLocationString(): String? {
                    return element.containingFile.name
                }

                override fun getIcon(unused: Boolean): Icon? {
                    return SimpleIcons.FILE
                }
            }
        }
    }
}