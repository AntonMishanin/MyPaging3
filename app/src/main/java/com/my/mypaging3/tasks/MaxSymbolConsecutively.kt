package com.my.mypaging3.tasks

/*
Найти максимальное число идущих подряд одинаковых символов в строке
 */

fun main() {
    //val input = "ddseeedssss"
    val input = ""

    var max = 0
    var consecutivelySize = 0
    var previousElement: Char? = null

    input.forEach { currentElement ->
        consecutivelySize = when (previousElement == currentElement) {
            true -> consecutivelySize + 1
            false -> 1
        }

        previousElement = currentElement

        //You can move it to false condition
        if (consecutivelySize >= max) max = consecutivelySize
    }

    println(max)
}