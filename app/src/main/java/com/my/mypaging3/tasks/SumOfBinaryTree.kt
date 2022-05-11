package com.my.mypaging3.tasks

/*
Нужно написать функцию, которая принимает бинарное дерево,
в узлах которого записаны целые числа.
Из этого дерева необходимо вернуть сумму значений всех листьев.
 */

data class Node(
    val value: Int,
    val leftNode: Node?,
    val rightNode: Node?
)

fun main() {
    val root = Node(value = 0, null, null)

    val sum = sumOfNode(root)

    println(sum)
}

fun sumOfNode(node: Node?): Int {

    if (node == null) {
        return 0
    }

    if (node.leftNode == null && node.rightNode == null) {
        return node.value
    }

    return sumOfNode(node.leftNode) + sumOfNode(node.rightNode)
}