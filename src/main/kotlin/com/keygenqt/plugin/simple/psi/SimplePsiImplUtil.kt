package com.keygenqt.plugin.simple.psi

import org.intellij.sdk.language.psi.*

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
    }
}