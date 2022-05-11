package com.my.mypaging3.tasks

/*
Пусть медиана массива $a_{0}, a_{1},...a_{n−1}$ определяется, как $⌊n / 2⌋$-й элемент в отсортированном порядке. Например:

Медиана массива [1, 3, 2] равна 2.
Медиана массива [5, 3, 2, 8] равна 5.
Медиана массива [7] равна 7.

Вам дан массив из n элементов $a_{0}, a_{1}, . . . a_{n−1}$.
Необходимо для каждого $i$ найти медиану среди массива
$a_{0}, a_{1}, . . . a_{i−1}, a_{i+1}, . . . a_{n−1}$ (исходный массив без элемента $a_{i}$).
 */

// Need refactoring
fun main() {

    val array = intArrayOf(0, 1, 2, 3)

    array.sort()


    if (array.size % 2 == 0) {
        printEven(array)
    } else {
        printOdd(array)
    }
}

// Четное
private fun printEven(array: IntArray) {
    for (i in array.indices) {

        val mid = if (i >= array.size / 2) {
            array.size / 2 - 1
        } else {
            array.size / 2
        }

        println(array[mid])
    }
}

// Нечетное
private fun printOdd(array: IntArray) {
    for (i in array.indices) {

        val mid = if (i >= array.size / 2) {
            array.size / 2
        } else {
            array.size / 2 + 1
        }

        println(array[mid])
    }
}