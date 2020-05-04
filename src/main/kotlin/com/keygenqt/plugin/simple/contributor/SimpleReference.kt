package com.keygenqt.plugin.simple.contributor

import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.keygenqt.plugin.simple.language.*
import com.keygenqt.plugin.simple.util.*
import java.util.*

class SimpleReference(element: PsiElement, textRange: TextRange) : PsiReferenceBase<PsiElement?>(element, textRange),
    PsiPolyVariantReference {

    private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = myElement!!.project
        val properties = SimpleUtil.findProperties(project, key)
        val results = arrayListOf<ResolveResult>()
        for (property in properties) {
            results.add(PsiElementResolveResult(property))
        }
        return results.toTypedArray()
    }

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants(): Array<Any> {
        val project = myElement!!.project
        val properties = SimpleUtil.findProperties(project)
        val variants: MutableList<LookupElement> = ArrayList()
        for (property in properties) {
            property.key?.let {
                if (it.isNotEmpty()) {
                    variants.add(LookupElementBuilder.create(property).withIcon(SimpleIcons.FILE)
                        .withTypeText(property.containingFile.name))
                }
            }
        }
        return variants.toTypedArray()
    }
}