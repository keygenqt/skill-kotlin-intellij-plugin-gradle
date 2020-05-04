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