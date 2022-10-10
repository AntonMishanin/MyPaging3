package com.my.mypaging3.tasks

/*
https://habr.com/ru/company/ozontech/blog/667728/

Разбор задачи «Электронная таблица»

Задана прямоугольная таблица nXm из чисел.
Требуется обработать k запросов: примените стабильную сортировку по неубыванию к столбцу i.

Требуется вывести таблицу после обработки всех запросов.
 */

fun main() {

    val m = mutableListOf(
        listOf(0, 0, 0, 0),
        listOf(2, 2, 2, 2),
        listOf(3, 3, 3, 3),
        listOf(1, 1, 1, 1)
    )
    val commands = listOf(0)

    for (j in commands.indices) {
        val columnNumber = commands[j]
        repeat(m.size-1){
            for (i in 0..m.size - 2) {
                val stringI = m[i]
                val stringNextI = m[i + 1]
                if (stringNextI[columnNumber] < stringI[columnNumber]) {
                    m[i + 1] = stringI
                    m[i] = stringNextI
                }
            }
        }
    }

    m.forEach {
        println(it)
    }
}