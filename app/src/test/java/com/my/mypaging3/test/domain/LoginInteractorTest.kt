package com.my.mypaging3.test.domain

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


class LoginInteractorTest {

    private val emailValidator = mock<EmailValidator>()

    @Test
    fun logIn_successLogin_Success() {
        val validator = Validator.FakeTrue()
        val loginInteractor = LoginInteractor(validator)
        assertEquals(LoginState.Success, loginInteractor.logIn("sfdfsfds@mail.ru"))
    }

    @Test
    fun logIn_errorLogin_Error() {
        val validator = Validator.FakeFalse()
        val loginInteractor = LoginInteractor(validator)
        assertEquals(LoginState.Error, loginInteractor.logIn(""))
    }

    @Test
    fun logIn_exceptionLogin_Error() {
        val validator = Validator.FakeException()
        val loginInteractor = LoginInteractor(validator)
        assertEquals(LoginState.Error, loginInteractor.logIn(null))
    }

    @Test
    fun logIn_mockSuccessLogin_Success() {
        val email = "sfdfsfds@mail.ru"
        Mockito.`when`(emailValidator.isValid(email)).thenReturn(true)
        val loginInteractor = LoginInteractor(emailValidator)
        assertEquals(LoginState.Success, loginInteractor.logIn(email))
    }

    @Test
    fun logIn_mockErrorLogin_Error() {
        Mockito.`when`(emailValidator.isValid("")).thenReturn(false)
        val loginInteractor = LoginInteractor(emailValidator)
        assertEquals(LoginState.Error, loginInteractor.logIn(""))
    }

    @Test
    fun logIn_mockExceptionLogin_Error() {
        Mockito.`when`(emailValidator.isValid("")).thenThrow(NullPointerException(""))
        val loginInteractor = LoginInteractor(emailValidator)
        assertEquals(LoginState.Error, loginInteractor.logIn(null))
    }
}

