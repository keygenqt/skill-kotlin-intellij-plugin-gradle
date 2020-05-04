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