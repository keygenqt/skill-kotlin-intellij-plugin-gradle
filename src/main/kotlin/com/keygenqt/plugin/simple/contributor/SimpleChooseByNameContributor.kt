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