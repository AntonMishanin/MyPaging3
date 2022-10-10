package com.my.mypaging3.tasks

/*
https://habr.com/ru/company/ozontech/blog/667728/

Разбор задачи «Адресная книга»
Дается журнал звонков — набор записей (имя звонившего, телефон звонившего).
Записи даны в хронологическом порядке от наиболее ранней к самой последней.
Требуется восстановить для каждого звонившего 5 последних его номеров телефона.

Записи могут встречаться несколько раз, то есть возможна ситуация,
когда одна пара (имя звонившего, телефон звонившего) встречается два и более раза во входных данных.
 */

fun main() {
    val names = listOf("N1", "N2", "N1", "N1", "N2", "N1", "N1", "N1", "N1")
    val phones = listOf("P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9")

    val uniqueCallers = HashMap<String, HashSet<String>>()
    for (i in names.size - 1 downTo 0) {
        val name = names[i]
        val phone = phones[i]

        val userPhones = uniqueCallers[name]?.size ?: 0
        if (userPhones < 5) {
            val set = uniqueCallers[name] ?: HashSet()
            set.add(phone)
            uniqueCallers[name] = set
        }
    }

    println(uniqueCallers)
}