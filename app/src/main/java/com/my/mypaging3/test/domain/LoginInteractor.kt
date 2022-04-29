package com.my.mypaging3.test.domain

class LoginInteractor(
    private val emailValidator: Validator
) {

    fun logIn(email: String?): LoginState {
        return try {
            when (emailValidator.isValid(email)) {
                true -> LoginState.Success()
                false -> LoginState.Error()
            }
        } catch (e: NullPointerException) {
            LoginState.Error()
        }
    }
}