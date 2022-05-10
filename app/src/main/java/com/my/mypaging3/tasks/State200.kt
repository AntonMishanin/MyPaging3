package com.my.mypaging3.tasks

import kotlin.math.sqrt

/*
Вам дан массив натуральных чисел ai.
Найдите число таких пар элементов (ai,aj), где ∣∣ai−aj∣∣%200==0 и i<j.
 */

fun main() {
   // val a = intArrayOf(203, 404, 204, 200, 403)
    val a = intArrayOf(2022, 2020, 2021)

    var count = 0
    for (i in a.indices) {
        for (j in i + 1 until a.size) {
            if (module(a[i], a[j]) % 200 == 0) count++
        }
    }

    println(count)
}

private fun module(value1: Int, value2: Int): Int {
    val diff = (value1 - value2).toDouble()
    return sqrt(diff * diff).toInt()
}