package com.keygenqt.plugin.simple.grammar

import com.intellij.psi.tree.IElementType
import com.keygenqt.plugin.simple.language.SimpleLanguage
import org.jetbrains.annotations.NonNls

class SimpleTokenType(@NonNls debugName: String) : IElementType(debugName, SimpleLanguage.INSTANCE) {
    override fun toString(): String {
        return "SimpleTokenType." + super.toString()
    }
}