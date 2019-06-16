package com.github.adelina609.stackoverrelations.di.app.component

import android.content.Context
import android.content.SharedPreferences
import com.github.adelina609.stackoverrelations.data.local.dao.AnswerDao
import com.github.adelina609.stackoverrelations.data.local.dao.QuestionDao
import com.github.adelina609.stackoverrelations.data.network.SorApi
import com.github.adelina609.stackoverrelations.di.app.module.*
import com.github.adelina609.stackoverrelations.di.app.scope.ApplicationScope
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@ApplicationScope
@Component(
    modules = [ContextModule::class, DataDBModule::class, DataNetModule::class, SharedPreferencesModule::class,
        NavigationModule::class]
)
interface AppComponent {
    fun provideApp(): Context
    fun provideSorApi(): SorApi
    fun provideQuestionDao(): QuestionDao
    fun provideAnswerDao() :AnswerDao
    fun provideSharedPreferences(): SharedPreferences
    fun provideNavigationRouter(): Router
    fun provideNavigatorHolder(): NavigatorHolder
}
