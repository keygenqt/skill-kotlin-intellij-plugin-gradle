package com.keygenqt.plugin.simple.lexer

import com.intellij.lexer.FlexAdapter
import org.intellij.sdk.language.lexer.SimpleLexer
import java.io.Reader

class SimpleLexerAdapter : FlexAdapter(SimpleLexer(null as Reader?))