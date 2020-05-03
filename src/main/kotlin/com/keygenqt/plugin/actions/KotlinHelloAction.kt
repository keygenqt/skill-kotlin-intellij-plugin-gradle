package com.keygenqt.plugin.actions

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import org.jetbrains.annotations.NotNull

class KotlinHelloAction : AnAction() {
    override fun actionPerformed(@NotNull e: AnActionEvent) {
        Messages.showInfoMessage("Hello Kotlin!\n", "Hello")
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.icon = AllIcons.General.Information
    }
}