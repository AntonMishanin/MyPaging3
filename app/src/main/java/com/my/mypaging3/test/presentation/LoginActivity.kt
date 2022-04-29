package com.my.mypaging3.test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.mypaging3.R
import com.my.mypaging3.databinding.ActivityLoginBinding
import com.my.mypaging3.test.core.Logger
import com.my.mypaging3.test.domain.EmailValidator
import com.my.mypaging3.test.domain.LoginInteractor
import com.my.mypaging3.test.domain.LoginState

class LoginActivity : AppCompatActivity() {

    //TODO: use ViewModelProvider
    private val viewModel = LoginViewModel(LoginInteractor(EmailValidator()), Logger.Fake())
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            viewModel.logInClicked(binding.email.text.toString())
        }

        viewModel.state.observe(this) {
            it.handle(binding)
        }
    }
}