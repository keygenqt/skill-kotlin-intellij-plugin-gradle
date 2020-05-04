package com.keygenqt.plugin.simple.contributor

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.keygenqt.plugin.simple.file.*
import org.intellij.sdk.language.psi.*

class SimpleElementFactory {
    companion object {
        @JvmStatic
        fun createProperty(project: Project?, name: String?): SimpleProperty {
            val file = createFile(project, name)
            return file.firstChild as SimpleProperty
        }

        @JvmStatic
        fun createFile(project: Project?, text: String?): SimpleFile {
            val name = "dummy.simple"
            return PsiFileFactory.getInstance(project)
                .createFileFromText(name, SimpleFileType.INSTANCE, text!!) as SimpleFile
        }
    }
}