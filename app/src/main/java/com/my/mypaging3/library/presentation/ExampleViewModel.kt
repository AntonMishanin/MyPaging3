package com.my.mypaging3.library.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.my.mypaging3.library.domain.SomeInteractor

class ExampleViewModel(
    private val interactor: SomeInteractor
) : ViewModel() {

    val content = Pager(PagingConfig(pageSize = 20)) {
        interactor
    }.flow
}