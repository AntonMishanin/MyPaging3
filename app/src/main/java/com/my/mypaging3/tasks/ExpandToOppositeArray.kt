package com.my.mypaging3.tasks

/*
Развернуть массив
 */

/*fun main() {
    val array = intArrayOf(0, 3, 5, 7)
    val result = arrayOfNulls<Int>(array.size)

    for (i in array.size - 1 downTo 0) {
        result[array.size - 1 - i] = array[i]
    }

    result.forEach {
        print(it)
    }
}*/

fun main() {
    val array = arrayListOf(0, 3, 5, 7)

    for(i in 0 until array.size){
        val lastIndex = array.size - 1 - i
        val element = array.removeAt(lastIndex)
        array.add(element)
    }

    println(array.toString())
}