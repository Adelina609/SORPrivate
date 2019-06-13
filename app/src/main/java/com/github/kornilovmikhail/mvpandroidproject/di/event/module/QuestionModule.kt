package com.github.kornilovmikhail.mvpandroidproject.di.event.module

import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.QuestionDao
import com.github.kornilovmikhail.mvpandroidproject.data.network.SorApi
import com.github.kornilovmikhail.mvpandroidproject.data.repository.PaginationPreferencesRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsDBRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsNetworkRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.QuestionScope

import dagger.Module
import dagger.Provides

@Module
class QuestionModule {
    @Provides
    @QuestionScope
    fun provideQuestionsRepo(
        questionsDBRepo: QuestionsDBRepo,
        questionsNetworkRepo: QuestionsNetworkRepo
    ): QuestionsRepo = QuestionsRepo(questionsDBRepo, questionsNetworkRepo, providePagination())

    @Provides
    @QuestionScope
    fun providePagination(): PaginationPreferencesRepo =
        PaginationPreferencesRepo()

    @Provides
    @QuestionScope
    fun provideQuestionsNetworkRepo(sorApi : SorApi): QuestionsNetworkRepo =
        QuestionsNetworkRepo(sorApi)

    @Provides
    @QuestionScope
    fun provideQuestionsDBRepo(questionDao: QuestionDao): QuestionsDBRepo = QuestionsDBRepo(questionDao)
}
