package com.my.mypaging3.tasks

/*
Найти максимальную длину неповторяющихся элементов в строке
 */

fun main() {

    val input = "1231456"
    val value = 1
    val uniqueContainer = HashMap<Char, Int>()

    var max = 0
    for (element in input) {
        if (uniqueContainer.containsKey(element)){
            uniqueContainer.clear()
        }

        uniqueContainer[element] = value

        if (uniqueContainer.size > max){
            max = uniqueContainer.size
        }
    }

    println(max)
}