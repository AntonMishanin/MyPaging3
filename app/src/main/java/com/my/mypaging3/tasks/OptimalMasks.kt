package com.my.mypaging3.tasks

/*
Никита собирается открывать свой пункт выдачи заказов (ПВЗ).
Для обеспечения безопасности посетителей Никита решил обеспечить ПВЗ одноразовыми медицинскими масками,
которые будут поставляться каждый месяц в количестве $N$ штук.
Местные поставщики продают маски по цене 0,55 рублей за одну штуку,
но можно сэкономить, купив упаковку из 24 масок за 11 рублей или коробку из 16 упаковок за 160 рублей.
 Помогите Никите, определив, сколько коробок,
 упаковок и отдельных масок он должен купить, чтобы заплатить как можно меньше денег.

Примечание: Никита готов купить больше масок, чем ему нужно, если это позволит сэкономить.

Формат входных данных

В одной строке вводится одно целое число $N (1 ≤ N ≤ 50000)$.

Формат выходных данных

Требуется вывести три числа в одну строку через пробел —
количество отдельных масок, упаковок и коробок, которые надо купить.
 */

fun main() {
    val n = 49

    var resultMasks = 0
    var resultBox = 0
    var resultBigBox = 0

    var sumOfMasks = 0
    while (sumOfMasks < n) {

        val difference = n - sumOfMasks

        when {
            difference >= 24 * 16 -> {
                //big masks
                resultBigBox++
                sumOfMasks += 24 * 16
            }
            difference / 24 * 11 <= difference * 0.55 && difference / 24 * 11 != 0 -> {
                //Middle masks
                resultBox++
                sumOfMasks += 24
            }
            (difference / 24 + 1) * 11 <= difference * 0.55 -> {
                //Middle masks
                resultBox++
                sumOfMasks += 24
            }
            else -> {
                //Masks
                resultMasks += difference
                sumOfMasks += difference
            }
        }
    }

    print("$resultMasks $resultBox $resultBigBox")
}
















