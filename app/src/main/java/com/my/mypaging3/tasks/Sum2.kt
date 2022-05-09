package com.my.mypaging3.tasks

/*
Дан массив целых чисел arr и целое число X,
нужно определить, существуют ли в массиве два элемента,
сумма которых в точности равна X
 */

fun main() {
    //val input = intArrayOf(0, 9, 4)
    //val input = intArrayOf(0, 2, -4, 4, 6, 11, -7)
    val input = intArrayOf(-1, 2, 4, 12, -6, 8, 15)
    val x = 8

    if (input.size <= 1) {
        print(false)
        return
    }

    var leftIndex = 0
    while (leftIndex < input.size) {

        for (i in leftIndex + 1 until input.size) {
            if (input[leftIndex] + input[i] == x) {
                println(true)
                return
            }
        }
        leftIndex++
    }

    print(false)
}