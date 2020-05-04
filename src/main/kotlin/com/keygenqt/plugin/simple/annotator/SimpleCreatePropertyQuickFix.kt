/*
 * Copyright 2020 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
