package com.my.mypaging3.auth

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.mypaging3.auth.github.KeyWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(
    private val authInteractor: AuthInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val state = MutableLiveData<>()

    fun authWithGithubClicked() {
        val disposable = authInteractor.authWithGithubClicked()
            .subscribe { response ->
                Log.d("EE", "response = $response")

                deviceCode = response.device_code

                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/device"))
                startActivity(intent)
            }
        compositeDisposable.add(disposable)
    }

    fun checkAuthClicked() {

    }

    fun createRepoClicked() {

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

sealed class State{
    class ShowSiteBy
}