package com.keygenqt.plugin.simple.grammar

import com.intellij.psi.tree.IElementType
import com.keygenqt.plugin.simple.language.SimpleLanguage
import org.jetbrains.annotations.NonNls

class SimpleElementType(@NonNls debugName: String) : IElementType(debugName, SimpleLanguage.INSTANCE)