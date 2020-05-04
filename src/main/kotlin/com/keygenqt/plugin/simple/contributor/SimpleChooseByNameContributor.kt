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

package com.keygenqt.plugin.simple.contributor

import com.intellij.navigation.*
import com.intellij.openapi.project.*
import com.keygenqt.plugin.simple.util.*

class SimpleChooseByNameContributor : ChooseByNameContributor {
    override fun getNames(project: Project, includeNonProjectItems: Boolean): Array<String> {
        val properties = SimpleUtil.findProperties(project)
        val names = arrayListOf<String>()
        for (property in properties) {
            property.key?.let {
                if (it.isNotEmpty()) {
                    names.add(it)
                }
            }
        }
        return names.toTypedArray()
    }

    override fun getItemsByName(name: String, pattern: String, project: Project,
        includeNonProjectItems: Boolean): Array<NavigationItem> {
        val result = arrayListOf<NavigationItem>()
        SimpleUtil.findProperties(project, name).forEach { result.add(it as NavigationItem) }
        return result.toTypedArray()
    }
}