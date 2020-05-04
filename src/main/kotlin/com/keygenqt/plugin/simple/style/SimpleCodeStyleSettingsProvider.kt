package com.keygenqt.plugin.simple.style

import com.intellij.application.options.*
import com.intellij.psi.codeStyle.*
import com.keygenqt.plugin.simple.language.*

class SimpleCodeStyleSettingsProvider : CodeStyleSettingsProvider() {

    override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings? {
        return SimpleCodeStyleSettings(settings)
    }

    override fun getConfigurableDisplayName(): String? {
        return "Simple"
    }

    override fun createConfigurable(settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings): CodeStyleConfigurable {
        return object : CodeStyleAbstractConfigurable(settings, modelSettings, this.configurableDisplayName) {
            override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
                return SimpleCodeStyleMainPanel(currentSettings, settings)
            }
        }
    }

    private class SimpleCodeStyleMainPanel(currentSettings: CodeStyleSettings?, settings: CodeStyleSettings?) :
        TabbedLanguageCodeStylePanel(SimpleLanguage.INSTANCE, currentSettings, settings)
}