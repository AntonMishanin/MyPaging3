package com.my.mypaging3.test.domain

interface Validator {
    fun isValid(value: String?): Boolean

    class FakeTrue : Validator {
        override fun isValid(value: String?) = true
    }

    class FakeFalse : Validator {
        override fun isValid(value: String?) = false
    }

    class FakeException : Validator {
        override fun isValid(value: String?) = throw NullPointerException("Value must be nonNull")
    }
}