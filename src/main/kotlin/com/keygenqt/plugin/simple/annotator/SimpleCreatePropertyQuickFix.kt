package com.keygenqt.plugin.simple.annotator

import com.intellij.codeInsight.intention.impl.*
import com.intellij.openapi.application.*
import com.intellij.openapi.command.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.fileChooser.*
import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.pom.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.util.*
import com.keygenqt.plugin.simple.file.*

internal class SimpleCreatePropertyQuickFix(private val key: String) : BaseIntentionAction() {
    override fun getText(): String {
        return "Create property"
    }

    override fun getFamilyName(): String {
        return "Simple properties"
    }

    override fun isAvailable(project: Project,
        editor: Editor, file: PsiFile): Boolean {
        return true
    }

    @Throws(IncorrectOperationException::class) override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        ApplicationManager.getApplication().invokeLater {
            val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(project))
            if (virtualFiles.isNotEmpty()) {
                createProperty(project, virtualFiles.iterator().next())
            } else {
                val descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor(SimpleFileType.INSTANCE)
                descriptor.setRoots(project.guessProjectDir())
                val file1 = FileChooser.chooseFile(descriptor, project, null)
                file1?.let { createProperty(project, it) }
            }
        }
    }

    private fun createProperty(project: Project, file: VirtualFile) {
        WriteCommandAction.writeCommandAction(project).run<RuntimeException> {
            val simpleFile = PsiManager.getInstance(project).findFile(file) as SimpleFile?
            simpleFile!!.node.lastChildNode?.let {
                simpleFile.node.addChild(SimpleElementFactory.createCRLF(project).node)
            }
            val property = SimpleElementFactory.createProperty(project, key.replace(" ".toRegex(), "\\\\ "), "")
            simpleFile.node.addChild(property.node)
            (property.lastChild.navigationElement as Navigatable).navigate(true)
            FileEditorManager.getInstance(project).selectedTextEditor?.caretModel?.moveCaretRelatively(2, 0, false,
                false, false)
        }
    }

}
