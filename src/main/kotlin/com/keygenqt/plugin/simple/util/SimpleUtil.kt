package com.keygenqt.plugin.simple.util

import com.intellij.navigation.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.search.*
import com.intellij.psi.util.*
import com.keygenqt.plugin.simple.file.*
import com.keygenqt.plugin.simple.language.*
import org.intellij.sdk.language.psi.*
import java.util.*
import javax.swing.*

class SimpleUtil {

    companion object {
        @JvmStatic
        fun findProperties(project: Project?, key: String): List<SimpleProperty> {
            val result: ArrayList<SimpleProperty> = ArrayList()
            project?.let {
                val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(it))
                for (virtualFile in virtualFiles) {
                    val simpleFile = PsiManager.getInstance(it).findFile(virtualFile)
                    if (simpleFile is SimpleFile) {
                        PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty::class.java)?.let { properties ->
                            for (property in properties) {
                                if (key == property.key) {
                                    result.add(property)
                                }
                            }
                        }
                    }
                }
            }
            return result
        }

        @JvmStatic
        fun findProperties(project: Project?): List<SimpleProperty> {
            val result: ArrayList<SimpleProperty> = ArrayList()
            project?.let {
                val virtualFiles = FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(it))
                for (virtualFile in virtualFiles) {
                    val simpleFile = PsiManager.getInstance(it).findFile(virtualFile)
                    if (simpleFile is SimpleFile) {
                        PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty::class.java)?.let { properties ->
                            Collections.addAll(result, *properties)
                        }
                    }
                }
            }
            return result
        }
    }
}