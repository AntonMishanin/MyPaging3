package com.my.mypaging3.tasks

/*
https://habr.com/ru/company/ozontech/blog/667728/

Разбор задачи «Многомодульный проект»
Есть n модулей, для каждого известны зависимости, которые нужны для его установки.
Есть m запросов — на каждой запрос нужно установить какой-то модуль,
предварительно поставив все его зависимости, игнорируя уже установленные модули.
 */

fun main() {
    val dependencies = listOf(Module(), Module(), Module(), Module())
    val installedModules = HashMap<String, Boolean>()

    for (i in dependencies.indices) {
        val dependency = dependencies[i]
        dependency.install(installedModules)
        installedModules[dependency.id] = true
    }
}

private data class Module(
    val id: String = "",
    val dependencies: List<Module> = emptyList()
) {

    fun install(installedModules: HashMap<String, Boolean>) {
        if (installedModules.containsKey(id)) return

        dependencies.forEach { it.install(installedModules) }
        installedModules[id] = true
    }
}
