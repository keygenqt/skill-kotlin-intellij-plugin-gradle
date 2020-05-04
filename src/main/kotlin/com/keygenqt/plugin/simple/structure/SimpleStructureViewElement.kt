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

package com.keygenqt.plugin.simple.structure

import com.intellij.ide.projectView.*
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.navigation.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.keygenqt.plugin.simple.file.*
import org.intellij.sdk.language.psi.*
import org.intellij.sdk.language.psi.impl.*

class SimpleStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement {

    override fun getValue(): Any {
        return myElement
    }

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return myElement.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return myElement.canNavigateToSource()
    }

    override fun getAlphaSortKey(): String {
        return myElement.name ?: ""
    }

    override fun getPresentation(): ItemPresentation {
        return myElement.presentation ?: PresentationData()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getChildren(): Array<TreeElement> {
        if (myElement is SimpleFile) {
            val properties = PsiTreeUtil.getChildrenOfTypeAsList(myElement, SimpleProperty::class.java)
            val treeElements = arrayListOf<TreeElement>()
            for (property in properties) {
                treeElements.add(SimpleStructureViewElement(property as SimplePropertyImpl))
            }
            return treeElements.toTypedArray()
        }
        return StructureViewTreeElement.EMPTY_ARRAY as Array<TreeElement>
    }
}