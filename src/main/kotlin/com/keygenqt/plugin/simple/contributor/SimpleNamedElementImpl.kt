package com.keygenqt.plugin.simple.contributor

import com.intellij.extapi.psi.*
import com.intellij.lang.*

abstract class SimpleNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), SimpleNamedElement