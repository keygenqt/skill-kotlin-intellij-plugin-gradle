package com.keygenqt.plugin.simple.provider

import com.intellij.lang.cacheBuilder.*
import com.intellij.lang.findUsages.*
import com.intellij.psi.*
import com.intellij.psi.tree.*
import com.keygenqt.plugin.simple.annotator.*
import com.keygenqt.plugin.simple.lexer.*
import org.intellij.sdk.language.psi.*

class SimpleFindUsagesProvider : FindUsagesProvider {

    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(SimpleLexerAdapter(),
            TokenSet.create(SimpleTypes.KEY),
            TokenSet.create(SimpleTypes.COMMENT),
            TokenSet.EMPTY)
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        return if (element is SimpleProperty) {
            "simple property"
        } else {
            ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return if (element is SimpleProperty) {
            element.key ?: ""
        } else {
            ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return if (element is SimpleProperty) {
            element.key + SimpleAnnotator.SIMPLE_SEPARATOR_STR + element.value
        } else {
            ""
        }
    }
}