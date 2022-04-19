package com.my.mypaging3.auth

import com.my.mypaging3.auth.github.KeyWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthInteractor() {

    private val gitHubDataSource by lazy {
        val retrofit = RemoteFactory().provideRetrofit()
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        GitHubDataSource(gitHubApi)
    }

    fun authWithGithubClicked() = gitHubDataSource
        .fetchSessionCode(KeyWrapper().provideClientId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}