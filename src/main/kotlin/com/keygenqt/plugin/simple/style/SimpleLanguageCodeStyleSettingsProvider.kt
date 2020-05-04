package com.keygenqt.plugin.simple.style

import com.intellij.lang.*
import com.intellij.psi.codeStyle.*
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider.SettingsType.*
import com.keygenqt.plugin.simple.language.*

class SimpleLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {

    override fun getLanguage(): Language {
        return SimpleLanguage.INSTANCE
    }

    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
        if (settingsType == SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS")
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator")
        } else if (settingsType == BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE")
        }
    }

    override fun getCodeSample(settingsType: SettingsType): String? {
        return """# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
website = https://en.wikipedia.org/

language = English
# The backslash below tells the application to continue reading
# the value onto the next line.
message = Welcome to \
          Wikipedia!
# Add spaces to the key
key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
# Unicode
tab : \u0009"""
    }
}
