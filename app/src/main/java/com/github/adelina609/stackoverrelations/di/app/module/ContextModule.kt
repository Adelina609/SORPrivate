package com.github.adelina609.stackoverrelations.di.app.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.adelina609.stackoverrelations.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import kotlin.contracts.contract

@Module
class ContextModule(private val app: Application) {
    @Provides
    @ApplicationScope
    fun provideContext(): Context = app.applicationContext
}
