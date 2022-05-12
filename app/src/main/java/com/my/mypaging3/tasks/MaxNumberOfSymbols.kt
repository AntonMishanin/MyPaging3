package com.my.mypaging3.tasks

/*
Для этого нужно заменить каждый символ строки, кроме самого частого, на символ «*».

Формат входных данных

В единственной строке задана непустая строка из строчных латинских букв,
длина которой не превышает 100 символов.
Гарантируется, что среди символов строки есть такой,
частота которого строго больше частоты остальных символов.

Формат выходных данных

Выведите искомую строку с заменёнными символами.
 */

fun main() {

    val input = "adaacvbn"

    val container = HashMap<Char, Int>()
    var max = 0
    var symbol = '['

    // Find most popular symbol
    for (element in input) {
        container[element] = (container[element] ?: 0) + 1

        if (container[element] ?: 0 > max) {
            max = container[element] ?: 0
            symbol = element
        }
    }

    // Print or popular symbol or *
    for (element in input) {
        val value = when (element == symbol) {
            true -> element
            false -> "*"
        }
        print(value)
    }
}