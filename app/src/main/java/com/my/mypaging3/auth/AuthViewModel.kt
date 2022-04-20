package com.my.mypaging3.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.mypaging3.auth.github.AuthState
import io.reactivex.disposables.CompositeDisposable

class AuthViewModel(
    private val authInteractor: AuthInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    //TODO: apply encapsulation
    val state = MutableLiveData<AuthState>()

    fun authWithGithubClicked() {
        val disposable = authInteractor.authWithGithubClicked()
            .subscribe { response ->
                state.value = response
            }
        compositeDisposable.add(disposable)
    }

    fun checkAuthClicked() {
        val disposable = authInteractor.checkAuth().subscribe(state::setValue)
        compositeDisposable.add(disposable)
    }

    fun createRepoClicked() {
        val disposable = authInteractor.createRepoByName().subscribe(state::setValue)
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}