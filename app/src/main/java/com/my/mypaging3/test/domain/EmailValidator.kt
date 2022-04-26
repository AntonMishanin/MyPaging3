package com.my.mypaging3.test.domain

open class EmailValidator : Validator {
    override fun isValid(value: String?): Boolean {
        if (value == null) throw NullPointerException("Value must be nonNull")
        if (value.isEmpty()) return false
        if (value.length < 6) return false
        if (!value.contains("@")) return false
        if (!value.contains(".")) return false
        return true
    }
}