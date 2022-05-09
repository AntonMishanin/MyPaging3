package com.my.mypaging3.tasks

/*
Рассмотрим функцию, которая принимает на вход массив целых чисел.
Она находит префикс с максимальной суммой
и возвращает одно число — сумму чисел этого префикса.
 */

fun main() {
    val input: Array<Int> = arrayOf(-18, 2, -42)
    //val input: Array<Int> = arrayOf(18, -15, -42, 9, 11, -5)

    if (input.isEmpty()) {
        print("NULL")
        return
    }

    var max = input[0]
    var prefixSum = input[0]

    for (i in 1 until input.size) {
        prefixSum += input[i]

        if (prefixSum >= max) {
            max = prefixSum
        }
    }

    print(max)
}