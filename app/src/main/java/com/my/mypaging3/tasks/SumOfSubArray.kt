package com.my.mypaging3.tasks

/*
Дан массив из $n$ элементов $a_{0}, a_{1}, . . . a_{n−1}$ и число $X$.
Вам требуется найти число подмассивов $[a_{l},a_{l+1},...a_{r}]$,
для которых сумма $a_{l} +a_{l+1} +···+a_{l}$ делится на $X$ без остатка.
 */

fun main() {
    val x = 3
    val array = intArrayOf(1, 2, 2, 1)

    var count = 0
    var sum = 0
    for (i in array.indices) {

        for (j in i until array.size) {
            sum += array[j]
            if (sum % x == 0) {
                count++
            }
        }
        sum = 0
    }
    println(count)
}