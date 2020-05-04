package com.keygenqt.plugin.simple.structure

import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.StructureViewModel.*
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.psi.*

class SimpleStructureViewModel(psiFile: PsiFile?) : StructureViewModelBase(psiFile!!,
    SimpleStructureViewElement(psiFile)),
    ElementInfoProvider {
    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return false
    }
}