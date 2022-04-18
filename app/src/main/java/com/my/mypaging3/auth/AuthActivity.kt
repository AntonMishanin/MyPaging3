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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        findViewById<View>(R.id.use_git_for_auth).setOnClickListener {
            tryLogin()
        }
    }

    private fun requestAuth() {
        val redirectUri =
            "fabian://callback"// "http://127.0.0.1:4567"  // "http://127.0.0.1:4567/callback"
        val uri =
            Uri.parse("https://github.com/login/oauth/authorize?client_id=${KeyWrapper().provideClientId()}&scope=repo&redirect_uri=$redirectUri")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun tryLogin() {
        val retrofit = RemoteFactory().provideRetrofit()
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        val gitHubDataSource = GitHubDataSource(gitHubApi)
        gitHubDataSource.fetchSessionCode(KeyWrapper().provideClientId())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { sessionCode ->
                Log.d("EE", "sessionCode = $sessionCode")

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/device"))
                startActivity(intent)
            }
    }
}