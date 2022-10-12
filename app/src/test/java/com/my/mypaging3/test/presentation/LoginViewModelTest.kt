package com.my.mypaging3.test.presentation

import com.my.mypaging3.test.domain.LoginInteractor
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.my.mypaging3.test.core.Logger
import com.my.mypaging3.test.domain.LoginState
import org.junit.Assert

/*
I receive exception, then liveData set value:
"Method getMainLooper in android.os.Looper not mocked"
Solution:
https://stackoverflow.com/questions/45988310/setvalue-and-postvalue-on-mutablelivedata-in-unittest
 */

/*
internal class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `should invoke login with param`() {
        val loginInteractor = mock<LoginInteractor>()
        val viewModel = provideViewModel(loginInteractor)
        val email = "same_email"

        viewModel.logInClicked(email)

        Mockito.verify(loginInteractor, times(1)).logIn(email)
    }

    @Test
    fun `default state should be loading`() {
        val viewModel = provideViewModel()

        Assert.assertEquals(LoginState.Loading(), viewModel.state.value)
    }

    @Test
    fun `should return success state`() {
        val loginInteractor = mock<LoginInteractor>()
        val viewModel = provideViewModel(loginInteractor)
        val email = "same_email"
        val resultState = LoginState.Success()
        Mockito.`when`(loginInteractor.logIn(email)).thenReturn(resultState)

        viewModel.logInClicked(email)

        Assert.assertEquals(resultState, viewModel.state.value)
    }

    @Test
    fun `should return error state`() {
        val loginInteractor = mock<LoginInteractor>()
        val viewModel = provideViewModel(loginInteractor)
        val email = "same_email"
        val resultState = LoginState.Error()
        Mockito.`when`(loginInteractor.logIn(email)).thenReturn(resultState)

        viewModel.logInClicked(email)

        Assert.assertEquals(resultState, viewModel.state.value)
    }

    private fun provideViewModel(
        loginInteractor: LoginInteractor = mock(),
        logger: Logger = Logger.Fake()
    ) = LoginViewModel(loginInteractor, logger)
}*/
