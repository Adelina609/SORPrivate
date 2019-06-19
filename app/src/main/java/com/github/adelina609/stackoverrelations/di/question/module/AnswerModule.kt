package com.github.adelina609.stackoverrelations.di.question.module

import com.github.adelina609.stackoverrelations.data.local.dao.AnswerDao
import com.github.adelina609.stackoverrelations.data.network.SorApi
import com.github.adelina609.stackoverrelations.data.repository.AnswersNetworkRepo
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.di.question.scope.AnswerScope
import dagger.Module
import dagger.Provides

@Module
class AnswerModule {
    @Provides
    @AnswerScope
    fun provideAnswersRepo(
        AnswersNetworkRepo: AnswersNetworkRepo
    ): AnswersRepo = AnswersRepo(AnswersNetworkRepo)

    @Provides
    @AnswerScope
    fun provideAnswersNetworkRepo(sorApi : SorApi): AnswersNetworkRepo =
        AnswersNetworkRepo(sorApi)
}