package com.my.mypaging3.test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.mypaging3.R
import com.my.mypaging3.test.core.Logger
import com.my.mypaging3.test.domain.EmailValidator
import com.my.mypaging3.test.domain.LoginInteractor

class LoginActivity : AppCompatActivity() {

    private val viewModel = LoginViewModel(LoginInteractor(EmailValidator()), Logger.Fake())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}