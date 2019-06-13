package com.github.kornilovmikhail.mvpandroidproject.di.app.module

import androidx.room.Room
import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.data.local.QuestionDatabase
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.QuestionDao

import com.github.kornilovmikhail.mvpandroidproject.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataDBModule {
    companion object {
        const val DATABASE_NAME = "sor_app.db"
    }

    @Provides
    @ApplicationScope
    fun provideQuestionDatabase(context: Context): QuestionDatabase = Room.databaseBuilder(
        context,
        QuestionDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @ApplicationScope
    fun provideQuestionDao(questionDatabase: QuestionDatabase): QuestionDao = questionDatabase.questionDao()
}
