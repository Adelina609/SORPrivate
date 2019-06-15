package com.github.kornilovmikhail.mvpandroidproject.di.app.module

import androidx.room.Room
import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.data.local.CommonDatabase
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.AnswerDao
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
    fun provideQuestionDatabase(context: Context): CommonDatabase = Room.databaseBuilder(
        context,
        CommonDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @ApplicationScope
    fun provideQuestionDao(commonDatabase: CommonDatabase): QuestionDao = commonDatabase.questionDao()

    @Provides
    @ApplicationScope
    fun provideAnswerDao(commonDatabase: CommonDatabase) : AnswerDao = commonDatabase.answerDao()
}
