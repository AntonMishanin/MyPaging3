package com.my.mypaging3.tasks

/*
Даны 2 строки. Определить, отличаются ли они только порядком следования букв
 */

fun main() {

    //val firstString = "abc".split("")
    //val secondString = "bca".split("") as ArrayList<String>

    val firstString = "abcac".split("")
    val secondString = "bcaa".split("") as ArrayList<String>

    if (firstString.size != secondString.size) {
        println(false)
        return
    }

    for (i in firstString.indices) {

        val index = getIndexOfElementOrNull(secondString, firstString[i])
        if (index == null) {
            println(false)
            return
        } else {
            secondString.removeAt(index)
        }
    }

    println(true)
}

private fun getIndexOfElementOrNull(array: List<String>, element: String): Int? {
    for (j in array.indices) {
        if (element == array[j]) {
            return j
        }
    }
    return null
}