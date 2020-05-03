package com.keygenqt.plugin.simple.file

import com.intellij.openapi.fileTypes.LanguageFileType
import com.keygenqt.plugin.simple.language.SimpleIcons
import com.keygenqt.plugin.simple.language.SimpleLanguage
import javax.swing.Icon

class SimpleFileType private constructor() : LanguageFileType(SimpleLanguage.INSTANCE) {

    companion object {
        val INSTANCE = SimpleFileType()
    }

    override fun getName(): String {
        return "Simple File"
    }

    override fun getDescription(): String {
        return "Simple language file"
    }

    override fun getDefaultExtension(): String {
        return "simple"
    }

    override fun getIcon(): Icon? {
        return SimpleIcons.FILE
    }
}