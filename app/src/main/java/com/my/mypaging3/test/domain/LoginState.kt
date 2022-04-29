package com.my.mypaging3.test.domain

import android.view.View
import com.my.mypaging3.databinding.ActivityLoginBinding

interface LoginState {

    fun handle(binding: ActivityLoginBinding)

    class Success : LoginState {
        override fun handle(binding: ActivityLoginBinding) {
            binding.success.visibility = View.VISIBLE
            binding.error.visibility = View.GONE
        }
    }

    class Error : LoginState {
        override fun handle(binding: ActivityLoginBinding) {
            binding.success.visibility = View.GONE
            binding.error.visibility = View.VISIBLE
        }
    }

    class Loading : LoginState {
        override fun handle(binding: ActivityLoginBinding) {
            binding.success.visibility = View.GONE
            binding.error.visibility = View.GONE
        }
    }
}