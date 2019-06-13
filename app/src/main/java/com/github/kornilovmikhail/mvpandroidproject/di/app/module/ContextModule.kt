package com.github.kornilovmikhail.mvpandroidproject.di.app.module

import android.app.Application
import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val app: Application) {
    @Provides
    @ApplicationScope
    fun provideContext(): Context = app.applicationContext
}
