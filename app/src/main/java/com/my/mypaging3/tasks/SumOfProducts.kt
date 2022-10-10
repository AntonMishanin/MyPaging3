package com.my.mypaging3.tasks

/*
https://habr.com/ru/company/ozontech/blog/667728/

Задача «Сумма к оплате»

Дан массив цен за список продуктов, купленных в магазине.
Товары с одинаковой стоимостью считаются одинаковыми.

Требуется посчитать, сколько потребуется заплатить суммарно за весь поход в магазин при условии,
что в магазине проводится акция — «купи три одинаковых товара и заплати только за два».
 */

fun main() {
    //val pricesOfProducts = listOf(1, 2, 3, 1, 1, 2)// 9
    //val pricesOfProducts = listOf(1, 2, 3)// 6
    val pricesOfProducts = listOf(1, 2, 3, 3, 3)// 9
    val container = HashMap<Int, Int>()

    for (i in pricesOfProducts.indices) {
        val price = pricesOfProducts[i]
        val previousValue = container[price] ?: 0
        container[price] = previousValue + 1
    }

    var sum = 0
    container.keys.forEach { key ->
        val value = container[key] ?: 0
        sum += (value % 3 + (value / 3) * 2) * key
    }

    println(sum)
}