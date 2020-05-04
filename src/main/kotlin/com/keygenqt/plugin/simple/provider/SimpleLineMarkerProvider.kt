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

package com.keygenqt.plugin.simple.provider

import com.intellij.codeInsight.daemon.*
import com.intellij.codeInsight.navigation.*
import com.intellij.psi.*
import com.intellij.psi.impl.source.tree.java.*
import com.keygenqt.plugin.simple.annotator.*
import com.keygenqt.plugin.simple.language.*
import com.keygenqt.plugin.simple.util.SimpleUtil.Companion.findProperties

class SimpleLineMarkerProvider : RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>) {
        if (element !is PsiJavaTokenImpl || element.getParent() !is PsiLiteralExpression) return
        when (val literalExpression = element.getParent()) {
            is PsiLiteralExpression -> {
                val value = literalExpression.value
                if (value is String && value.startsWith(
                        SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)
                ) {
                    val project = element.getProject()
                    val possibleProperties = value.substring(
                        SimpleAnnotator.SIMPLE_PREFIX_STR.length + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length)
                    val properties = findProperties(project, possibleProperties)
                    if (properties.isNotEmpty()) {
                        val builder = NavigationGutterIconBuilder.create(SimpleIcons.FILE)
                            .setTargets(properties)
                            .setTooltipText("Navigate to Simple language property")
                        result.add(builder.createLineMarkerInfo(element))
                    }
                }
            }
        }

    }
}