package com.keygenqt.plugin.simple.annotator

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.keygenqt.plugin.simple.file.*
import org.intellij.sdk.language.psi.*

object SimpleElementFactory {
    fun createProperty(project: Project?, name: String?): SimpleProperty {
        val file = createFile(project, name)
        return file.firstChild as SimpleProperty
    }

    fun createFile(project: Project?, text: String?): SimpleFile {
        val name = "dummy.simple"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, SimpleFileType.INSTANCE, text!!) as SimpleFile
    }

    fun createProperty(project: Project?, name: String,
        value: String): SimpleProperty {
        val file = createFile(project, "$name = $value")
        return file.firstChild as SimpleProperty
    }

    fun createCRLF(project: Project?): PsiElement {
        val file = createFile(project, "\n")
        return file.firstChild
    }
}