package com.keygenqt.plugin.simple.structure

import com.intellij.ide.structureView.*
import com.intellij.lang.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*

class SimpleStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder? {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return SimpleStructureViewModel(psiFile)
            }
        }
    }
}