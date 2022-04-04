package com.my.mypaging3.library.di

import com.my.mypaging3.library.data.local.LocalUserDataSource
import com.my.mypaging3.library.data.local.LocalUserRepository
import com.my.mypaging3.library.domain.SomeInteractor
import com.my.mypaging3.library.presentation.ExampleViewModel

class LibraryFactory {
    fun provideViewModel(): ExampleViewModel {
        val localUserDataSource = LocalUserDataSource()
        val localUserRepository = LocalUserRepository(localUserDataSource)

        val someInteractor = SomeInteractor(localUserRepository)

        return ExampleViewModel(someInteractor)
    }
}