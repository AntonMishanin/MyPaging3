package com.my.mypaging3.custom.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.mypaging3.custom.domain.CustomPagingInteractor
import com.my.mypaging3.custom.domain.PagingState
import com.my.mypaging3.library.data.local.LocalUserDataSource
import com.my.mypaging3.library.data.local.LocalUserRepository
import com.my.mypaging3.library.domain.User
import kotlinx.coroutines.launch

class CustomPagingViewModel : ViewModel() {

    private val localDataSource = LocalUserDataSource()
    private val repository = LocalUserRepository(localDataSource)
    private val interactor = CustomPagingInteractor(repository)

    val contentObservable = MutableLiveData<List<User>>()

    init {
        fetchUsersByPage(5)
    }

    fun fetchUsersByPage(page: Int) {
        viewModelScope.launch {
            val state = interactor.fetchUsersByPage(page)
            if (state is PagingState.Content) {
                contentObservable.value = state.users
            }
        }
    }
}