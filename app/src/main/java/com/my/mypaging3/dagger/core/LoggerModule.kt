package com.my.mypaging3.dagger.core

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoggerModule {

    @Singleton
    @Provides
    fun provideCustomLogger(context: Context): CustomLogger {
        return CustomLogger.Base(context)
    }
}