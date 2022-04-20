package com.my.mypaging3.auth

import android.net.Uri
import android.util.Log
import com.my.mypaging3.auth.github.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthInteractor {

    private var deviceCode = ""

    //TODO: store token in data layer and share it between interactors
    private var token = ""

    //TODO: use di
    private val gitHubDataSource by lazy {
        val retrofit = RemoteFactory().provideRetrofit()
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        GitHubDataSource(gitHubApi)
    }

    fun authWithGithubClicked(): Single<AuthState> = gitHubDataSource
        .fetchSessionCode(KeyWrapper().provideClientId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            deviceCode = it.device_code

            //TODO: fetch uri from response
            val uri = Uri.parse("https://github.com/login/device")
            AuthState.ShowSiteByUri(uri, it.device_code)
        }

    //TODO: handle error
    fun checkAuth(): Single<AuthState> = gitHubDataSource.checkToken(
        clientId = KeyWrapper().provideClientId(),
        deviceCode = deviceCode,
        grantType = "urn:ietf:params:oauth:grant-type:device_code"
    )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            Log.d("EE", it.toString())
            if (it is TokenResponse.Success) {
                token = "token " + it.access_token
                AuthState.SuccessAuth
            } else {
                AuthState.SuccessAuth//TODO: add fail state
            }
        }

    //TODO: move to another Interactor
    fun createRepoByName(name: String = "REPO_BY_DEVICE_2"): Single<AuthState> {
        //TODO: use di
        val retrofit = RemoteFactory(token).provideRetrofit(CONTENT_URL)
        val gitHubApi = retrofit.create(GitHubApi::class.java)
        val dataSource = GitHubDataSource(gitHubApi)
        return dataSource.createRepository(token, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                RepositoryResponse(-111)
            }
            .map {
                AuthState.SuccessRepoCreated
            }
    }
}