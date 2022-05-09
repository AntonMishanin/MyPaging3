package com.my.mypaging3.tasks

/*
Есть упорядоченный массив целых чисел arr,
нужно определить, есть ли в нём число X
 */

fun main() {
    val arr = intArrayOf(0, 1, 2, 4)
    val x = -1

    var leftLimit = 0
    var rightLimit = arr.size
    while (leftLimit < rightLimit) {

        val mid = (leftLimit + rightLimit) / 2

        if (arr[mid] == x) {
            print(true)
            return
        }

        if (arr[mid] > x) {
            rightLimit = mid
        } else {
            leftLimit = mid + 1//+1, because mid position was checked and go to next position
        }
    }

    print(false)
}