package com.my.mypaging3.test.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.mypaging3.BuildConfig
import com.my.mypaging3.R
import com.my.mypaging3.test.core.Logger
import com.my.mypaging3.test.domain.LoginInteractor
import com.my.mypaging3.test.domain.LoginState

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val logger: Logger
) : ViewModel() {

    private val _state = MutableLiveData<LoginState>(LoginState.Loading())
    val state: LiveData<LoginState> = _state

    fun logInClicked(email: String) {
        val loginState = loginInteractor.logIn(email)
        _state.value = loginState

        //Use textProvider
        val message = R.string.androidx_startup.toString()
        logger.log(message)

        //Use BuildConfigProvider
        BuildConfig.APPLICATION_ID
    }
}