package com.my.mypaging3.tasks

import java.io.FileWriter

/*
https://leetcode.com/problems/binary-tree-inorder-traversal/

Given the root of a binary tree, return the inorder traversal of its nodes' values.
 */

// It doesn't work
fun main() {

}

fun inorderTraversal(root: TreeNode?): List<Int> {
    val trees = mutableListOf<TreeNode>()
    if (root != null) {
        trees.add(root)
    } else {
        return emptyList()
    }

    var i = 0
    while (i >= 0) {
        val node = trees[i]
        if (node.left != null) trees.add(node.left!!)

        if (node.right != null) trees.add(node.right!!)

        i++

        if (node.left == null && node.right == null) i = -1
    }

    val output = mutableListOf<Int>()
    output.add(trees[0].`val`)
    for (j in trees.size - 1 downTo 1) {
        output.add(trees[j].`val`)
    }

    return output
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}