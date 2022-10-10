package com.my.mypaging3.tasks

/*
https://habr.com/ru/company/ozontech/blog/667728/

Разбор задачи «Подсистема регистрации»
На вход подаются n логинов, для каждого логина вам нужно сказать,
можно ли его зарегистрировать. Логин должен соответствовать следующим правилам:

1. Логин — это последовательность из латинских букв в любом регистре,
цифр и символов «_» или «-» (подчеркивание и дефис).
2. Логин не должен начинаться с дефиса.
3. Логин должен иметь длину от 2 до 24 символов.
4. Логины, которые отличаются только регистром, считаются одинаковыми.
(Видимо, при дублировании логин не пускать)
 */

fun main() {
    val logins = listOf("login1", "", "-qцу", "Login1")
    val goodLogins = HashMap<String, String>()

    logins.forEach { login ->
        // 4. Логины, которые отличаются только регистром, считаются одинаковыми.
        // (Видимо, при дублировании логин не пускать)
        val valid = if (isLoginValid(login)) {
            val lowercaseLogin = login.lowercase()
            if (goodLogins.contains(lowercaseLogin)) {
                false
            } else {
                goodLogins[lowercaseLogin] = ""
                true
            }
        } else {
            false
        }
        println(valid)
    }
}

private fun isLoginValid(login: String): Boolean {
    // 3. Логин должен иметь длину от 2 до 24 символов.
    if (login.length !in 2..24) return false

    // 2. Логин не должен начинаться с дефиса.
    if (login[0] == '-') return false

    // 1. Логин — это последовательность из латинских букв в любом регистре,
    // цифр и символов «_» или «-» (подчеркивание и дефис).
    for (i in login.indices) {
        val symbol = login[i]
        if (symbol == '-') continue
        if (symbol == '_') continue
        if (symbol.isDigit()) continue
        if (symbol.isLetter()) continue
        return false
    }
    return true
}
