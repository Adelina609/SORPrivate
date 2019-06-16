package com.github.adelina609.stackoverrelations.di.app.module

import android.app.Application
import android.content.Context
import com.github.adelina609.stackoverrelations.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val app: Application) {
    @Provides
    @ApplicationScope
    fun provideContext(): Context = app.applicationContext
}
