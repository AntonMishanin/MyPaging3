package com.my.mypaging3.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.my.mypaging3.R

class AuthActivity : AppCompatActivity() {

    //TODO: use view model factory
    private val viewModel by lazy {
        val authInteractor = AuthInteractor()
        AuthViewModel(authInteractor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        findViewById<View>(R.id.use_git_for_auth).setOnClickListener {
            viewModel.authWithGithubClicked()
        }

        findViewById<View>(R.id.check_token).setOnClickListener {
            viewModel.checkAuthClicked()
        }

        findViewById<View>(R.id.request).setOnClickListener {
            viewModel.createRepoClicked()
        }

        viewModel.state.observe(this){ state ->
            //TODO: check application context and memory leak
            state.handle(this)
        }
    }
}