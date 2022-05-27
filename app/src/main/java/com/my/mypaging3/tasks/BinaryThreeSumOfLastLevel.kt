package com.my.mypaging3.tasks

/*
Print sum of nodes from last level
 */

fun main() {
    val nodes = mutableListOf<Node>()

    var sum = 0

    while (nodes.isNotEmpty()) {

        sum = 0
        var iterations = 0

        while (iterations < nodes.size) {
            val node = nodes.removeAt(0)
            node.leftNode?.let { nodes.add(it) }
            node.rightNode?.let { nodes.add(it) }

            sum += node.value
            iterations++
        }
    }

    println(sum)
}