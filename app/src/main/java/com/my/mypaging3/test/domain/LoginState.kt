package com.my.mypaging3.test.domain

sealed class LoginState {
    object Success : LoginState()
    object Error : LoginState()
}