package com.github.adelina609.stackoverrelations.di.question.module

import com.github.adelina609.stackoverrelations.data.local.dao.QuestionDao
import com.github.adelina609.stackoverrelations.data.network.SorApi
import com.github.adelina609.stackoverrelations.data.repository.*
import com.github.adelina609.stackoverrelations.di.question.scope.QuestionScope

import dagger.Module
import dagger.Provides

@Module
class QuestionModule {
    @Provides
    @QuestionScope
    fun provideQuestionsRepo(
        questionsNetworkRepo: QuestionsNetworkRepo
    ): QuestionsRepo = QuestionsRepo(questionsNetworkRepo,
        providePagination()
    )

    @Provides
    @QuestionScope
    fun providePagination(): PreferencesRepo =
        PreferencesRepo()

    @Provides
    @QuestionScope
    fun provideQuestionsNetworkRepo(sorApi: SorApi): QuestionsNetworkRepo =
        QuestionsNetworkRepo(sorApi)

    @Provides
    @QuestionScope
    fun provideNotifNetworkRepo(sorApi: SorApi) : NotificationNetworkRepo =
        NotificationNetworkRepo(sorApi)
}
