package com.my.mypaging3.auth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.my.mypaging3.R
import com.my.mypaging3.auth.github.KeyWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthActivity : AppCompatActivity() {
    //"Authorization: token OAUTH-TOKEN"
    private var deviceCode = ""
    private var token = "token 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"

    private val viewModel by lazy {
        val authInteractor = AuthInteractor()
        AuthViewModel(authInteractor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        findViewById<View>(R.id.use_git_for_auth).setOnClickListener {
            // tryLogin()
            viewModel.authWithGithubClicked()
        }

        findViewById<View>(R.id.check_token).setOnClickListener {
            //checkToken()
            viewModel.checkAuthClicked()
        }

        findViewById<View>(R.id.request).setOnClickListener {
            //createRepository()
            viewModel.createRepoClicked()
        }
    }
//TODO: use webview
    private fun checkToken() {
        gitHubDataSource.checkToken(
            clientId = KeyWrapper().provideClientId(),
            deviceCode = deviceCode,
            grantType = "urn:ietf:params:oauth:grant-type:device_code"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                it.printStackTrace()
            }
            .subscribe { response ->
                Log.d("EE", "response = $response")
                token = response.access_token
            }
    }

    private fun createRepository() {
        val retrofit = RemoteFactory(token).provideRetrofit(CONTENT_URL)
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        val dataSource = GitHubDataSource(gitHubApi)
        dataSource.createRepository("REPO_BY_DEVICE")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                RepositoryResponse(-111)
            }
            .subscribe { response ->
                Log.d("EE", "response = $response")
            }
    }
}