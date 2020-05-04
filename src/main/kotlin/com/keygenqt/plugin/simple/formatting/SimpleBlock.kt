package com.keygenqt.plugin.simple.formatting

import com.intellij.formatting.*
import com.intellij.formatting.WrapType.*
import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.formatter.common.*
import java.util.*

class SimpleBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?,
    private val spacingBuilder: SpacingBuilder) : AbstractBlock(node, wrap, alignment) {

    override fun buildChildren(): List<Block> {
        val blocks = arrayListOf<Block>()
        var child = myNode.firstChildNode
        while (child != null) {
            if (child.elementType !== TokenType.WHITE_SPACE) {
                val block: Block =
                    SimpleBlock(child, Wrap.createWrap(NONE, false), Alignment.createAlignment(), spacingBuilder)
                blocks.add(block)
            }
            child = child.treeNext
        }
        return blocks
    }

    override fun getIndent(): Indent? {
        return Indent.getNoneIndent()
    }

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return spacingBuilder.getSpacing(this, child1, child2)
    }

    override fun isLeaf(): Boolean {
        return myNode.firstChildNode == null
    }
}