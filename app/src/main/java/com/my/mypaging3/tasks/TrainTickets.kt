package com.my.mypaging3.tasks

import java.util.*
import kotlin.collections.HashMap

/*
https://habr.com/ru/company/ozontech/blog/667728/

Разбор задачи «Система продажи билетов на поезда»
Есть n купе, пронумерованных от 1 до n, в каждом купе два места, поступают запросы трех видов:

1. Занять место, если оно еще не занято.
2. Освободить место, если оно занято.
3. Занять купе с наименьшим номером, в котором все места свободны.

Если в купе освободили все места — то оно также считается свободным.
 */

private const val TO_BOOK = "TO_BOOK"
private const val TO_BOOK_FULL = "TO_BOOK_FULL"
private const val RELEASE = "RELEASE"
private const val DELIMITER = " "

fun main() {
    val coupes = hashMapOf(
        1 to Coupe(id = 1, hashMapOf(1 to false, 2 to false)),
        2 to Coupe(id = 2, hashMapOf(1 to false, 2 to false)),
        3 to Coupe(id = 3, hashMapOf(1 to false, 2 to false)),
        4 to Coupe(id = 4, hashMapOf(1 to false, 2 to false)),
        5 to Coupe(id = 5, hashMapOf(1 to false, 2 to false)),
        6 to Coupe(id = 6, hashMapOf(1 to false, 2 to false))
    )

    val commands = listOf(
        "$TO_BOOK${DELIMITER}1${DELIMITER}2",
        TO_BOOK_FULL,
        "$TO_BOOK${DELIMITER}3${DELIMITER}1",
        TO_BOOK_FULL,
        TO_BOOK_FULL,
        "$RELEASE${DELIMITER}3${DELIMITER}1"
    )

    val sortedSeats = TreeSet<Coupe> { var1, var2 ->
        // Returns a negative integer, zero, or a positive integer
        // as the first argument is less than, equal to, or greater than the second.
        return@TreeSet var1.id - var2.id
    }

    coupes.keys.forEach { key ->
        val coupe = coupes[key]!!
        sortedSeats.add(coupe)
    }

    commands.forEach { command ->
        if (command.contains(TO_BOOK_FULL)) {
            val coupe = sortedSeats.first()// check notnull
            coupes[coupe.id]?.toBook()
            sortedSeats.remove(coupe)
        } else if (command.contains(TO_BOOK)) {
            val list = command.split(DELIMITER)
            val coupeNumber = list[1].toInt()
            val seatNumber = list[2].toInt()
            val coupe = coupes[coupeNumber]!!
            coupe.seats[seatNumber] = true

            sortedSeats.remove(coupe)
        } else if (command.contains(RELEASE)) {
            val list = command.split(DELIMITER)
            val coupeNumber = list[1].toInt()
            val seatNumber = list[2].toInt()
            val coupe = coupes[coupeNumber]!!
            coupe.seats[seatNumber] = false

            if (coupe.isEmpty()) sortedSeats.add(coupe)
        }
    }

    coupes.keys.forEach { key ->
        println("$key ${coupes[key]}")
    }
}

private fun Coupe.isEmpty(): Boolean {
    seats.keys.forEach { key ->
        if (seats[key] == true) {
            return false
        }
    }
    return true
}

private data class Coupe(
    val id: Int,
    val seats: HashMap<Int, Boolean>
){
    fun toBook(){
        seats.keys.forEach { key ->
            seats[key] = true
        }
    }
}
