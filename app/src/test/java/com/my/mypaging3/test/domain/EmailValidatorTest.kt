package com.my.mypaging3.test.domain

import org.junit.Assert.*

import org.junit.Test

class EmailValidatorTest {

    private val validator = EmailValidator()

    @Test
    fun isValid_emailIsCorrect_true() {
        assertEquals(true, validator.isValid("rr@rr."))
    }

    @Test
    fun isValid_nullEmail_fail() {
        try {
            validator.isValid(null)
            fail("Should have thrown NullPointerException")
        } catch (e: NullPointerException) {
        }
    }

    @Test
    fun isValid_emptyEmail_false() = assertFalse(validator.isValid(""))

    @Test
    fun isValid_emailShorter6_false() = assertFalse(validator.isValid("email5"))

    @Test
    //symbol @
    fun isValid_emailDoesNotContainSpecSymbol_false() =
        assertEquals(false, validator.isValid("email5rr"))

    @Test
    fun isValid_emailDoesNotContainDot_false() = assertEquals(false, validator.isValid("email5rr@"))
}