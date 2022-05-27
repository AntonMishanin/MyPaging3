package com.my.mypaging3.tasks

/*
Аналитики застройщика смогли точно спрогнозировать цену на ближайшие $N$ дней
(пронумеруем дни в хронологическом порядке от $0$ до $N-1$).
Теперь требуется определить, в какие из этих дней нужно продавать,
 чтобы по истечении $N$ дней заработать как можно больше денег.
 Известно, что стройка новых площадей происходит с равномерной скоростью $S$ кв.
 метров в сутки. А к нулевому дню объем построенной площади составлял $S$ кв. метров, при том что $S = 1$.

Формат входных данных

В первой строке вводится одно целое число $N (0 < N < 10000)$ — количество дней.
 Во второй строке вводится последовательность из $N$ целых положительных чисел —
 цена квадратного метра складской площади в каждый из дней.

Формат выходных данных

Вывести одно число — максимальное количество денег, которое может заработать компания-застройщик.
 */

fun main() {
    val prices = intArrayOf(100, 200, 150)
    var result = 0

    var i = 0
    while (i < prices.size) {
        val position = getPositionWithMaxPrice(i, prices)
        val numberOfMeters = (position + 1 - i)
        result += numberOfMeters * prices[position]
        i = position + 1
    }

    println(result)
}

private fun getPositionWithMaxPrice(start: Int, array: IntArray): Int {
    var max = 0
    var position = 0
    for (i in start until array.size) {
        if (array[i] > max) {
            max = array[i]
            position = i
        }
    }

    return position
}