package com.my.mypaging3.tasks

import kotlin.math.min

/*
Дан массив из $n$ элементов $a_0$, $a_1$,… $a_n-1$.
Также определим функцию префиксной суммы $prefix\_sum(i) = a_0 + a_1 + ... + a_i$.

Вам разрешено делать с массивом следующие операции:

Увеличить любой элемент массива на единицу.
Уменьшить любой элемент массива на единицу.

С помощью минимального количества таких операций вам необходимо получить новый массив,
для которого любая префиксная сумма $prefix\_sum(i)$ либо положительная, либо отрицательная,
и при этом для любого $i < n — 1$ знак $prefix\_sum(i)$ не должен быть равен знаку $prefix\_sum(i + 1)$.

Формат выходных данных

Выведите одно число — минимальное количество операций,
которые нужно сделать с массивом, чтобы он удовлетворял необходимым условиям.
 */

fun main() {
    val array = mutableListOf(0, 1, -2)

    val startFromNegative = getNumberOfSteps(false, array)
    val startFromPositive = getNumberOfSteps(true, array)

    print(min(startFromNegative, startFromPositive))
}

private fun getNumberOfSteps(startSignPositive: Boolean, input: List<Int>): Int {
    val array = input.toMutableList()
    var isPositive = startSignPositive

    var steps = 0
    var sumOfSubArray = 0
    for (i in array.indices) {

        while (isConditionBySignAvailable(isPositive, value = sumOfSubArray + array[i])) {
            array[i] = getNewValueBySign(isPositive, array[i])
            steps++
        }

        sumOfSubArray += array[i]
        isPositive = !isPositive
    }
    return steps
}

private fun isConditionBySignAvailable(isPositive: Boolean, value: Int) =
    when (isPositive) {
        true -> value <= 0
        false -> value >= 0
    }

private fun getNewValueBySign(isPositive: Boolean, element: Int) =
    when (isPositive) {
        true -> element + 1
        false -> element - 1
    }