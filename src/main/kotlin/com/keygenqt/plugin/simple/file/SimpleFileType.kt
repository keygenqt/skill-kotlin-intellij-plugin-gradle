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

package com.keygenqt.plugin.simple.file

import com.intellij.openapi.fileTypes.LanguageFileType
import com.keygenqt.plugin.simple.language.SimpleIcons
import com.keygenqt.plugin.simple.language.SimpleLanguage
import javax.swing.Icon

class SimpleFileType private constructor() : LanguageFileType(SimpleLanguage.INSTANCE) {

    companion object {
        @JvmField
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