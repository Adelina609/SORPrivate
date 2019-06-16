package com.github.adelina609.stackoverrelations.di.event.module

import com.github.adelina609.stackoverrelations.data.local.dao.AnswerDao
import com.github.adelina609.stackoverrelations.data.network.SorApi
import com.github.adelina609.stackoverrelations.data.repository.AnswersDBRepo
import com.github.adelina609.stackoverrelations.data.repository.AnswersNetworkRepo
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.di.event.scope.AnswerScope
import dagger.Module
import dagger.Provides

@Module
class AnswerModule {
    @Provides
    @AnswerScope
    fun provideAnswersRepo(
        AnswersDBRepo: AnswersDBRepo,
        AnswersNetworkRepo: AnswersNetworkRepo
    ): AnswersRepo = AnswersRepo(AnswersDBRepo, AnswersNetworkRepo)

    @Provides
    @AnswerScope
    fun provideAnswersNetworkRepo(sorApi : SorApi): AnswersNetworkRepo =
        AnswersNetworkRepo(sorApi)

    @Provides
    @AnswerScope
    fun provideAnswersDBRepo(AnswerDao: AnswerDao): AnswersDBRepo = AnswersDBRepo(AnswerDao)

}