package com.my.mypaging3.tasks

import java.lang.Exception

fun main() {
    println(
        Solution2().discountPrices(
            sentence = "$421454 $3140906424 $3",
            discount = 65
        )
    )
}

class Solution2 {
    fun discountPrices(sentence: String, discount: Int): String {
        var result = ""
        val input = sentence.split(" ")

        for (i in input.indices) {

            val price = getPrice(input[i])

            val newValue = if (price != null) {
                applyDiscount(price, discount)
            } else {
                input[i]
            }

            result += if (i == 0) {
                newValue
            } else {
                " $newValue"
            }
        }

        return result
    }

    private fun getPrice(value: String): Double? {
        // if (value.isEmpty()) return null

        // if (value[0] != '$') return null
        // value.removePrefix("$")
        // var container = ""
        // for (i in 1 until value.length) {
        //     if (value[i].isDigit() || value[i] == '.') {
        //         container += value[i]
        //     } else {
        //         return null
        //     }
        // }

        if (value.isEmpty()) return null

        if (value[0] != '$') return null

        return try {
            value.removePrefix("$").toDouble()
        } catch (e: Exception) {
            null
        }
    }

    private fun applyDiscount(price: Double, discount: Int): String {

        val discountValue = (price / 100) * discount

        val result = (price - discountValue).round().toBigDecimal().toPlainString()

        var newResult = ""
        var i = 0
        var isDot = false

        // do rounding
        result.forEach {

            if (it == '.') {
                isDot = true
            }

            if (isDot) {
                i++
            }

            newResult += it
        }

        if (i == 2) {
            newResult += "0"
            return "$$newResult"
        }

        if (i == 0) {
            newResult += ".00"
            return "$$newResult"
        }

        return "$$newResult"
    }

    private fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()
}