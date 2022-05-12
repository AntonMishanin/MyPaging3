package com.my.mypaging3.tasks

/*
Дано число n.
Вывести все правильные скобочные последоваетльности длины 2n
 */

fun main() {
    generate("", 0, 0, 2)
}

private fun generate(current: String, opened: Int, closed: Int, n: Int) {
    if (current.length == 2 * n) {
        if (opened == closed) {
            println(current)
        }
        return
    }

    val container = "$current("
    generate(container, opened + 1, closed, n)

    if (closed < opened) {
        val container2 = "$current)"
        generate(container2, opened, closed + 1, n)
    }
}