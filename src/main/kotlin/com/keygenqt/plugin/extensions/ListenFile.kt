package com.keygenqt.plugin.extensions

import com.intellij.openapi.fileEditor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.startup.*
import com.intellij.openapi.vfs.*

class ListenFile : StartupActivity {
    override fun runActivity(project: Project) {
        project.messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER,
            object : FileEditorManagerListener {
                override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
                    println("fileOpen")
                }

                override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
                    println("fileClose")
                }
            })
    }
}