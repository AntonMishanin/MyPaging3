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
    fun isValid_emailShouldBeNotEmpty_false() = assertFalse(validator.isValid(""))

    @Test
    fun isValid_emailShouldBeLonger6_false() = assertFalse(validator.isValid("ema@."))

    @Test
    //symbol @
    fun isValid_emailShouldHaveSpecSymbol_false() =
        assertEquals(false, validator.isValid("email5rr."))

    @Test
    fun isValid_emailShouldHaveDot_false() = assertEquals(false, validator.isValid("email5rr@"))
}