package com.github.kornilovmikhail.mvpandroidproject.di.event.module

import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.AnswerDao
import com.github.kornilovmikhail.mvpandroidproject.data.network.SorApi
import com.github.kornilovmikhail.mvpandroidproject.data.repository.PaginationPreferencesRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.AnswersDBRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.AnswersNetworkRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.AnswersRepo
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.AnswerScope
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