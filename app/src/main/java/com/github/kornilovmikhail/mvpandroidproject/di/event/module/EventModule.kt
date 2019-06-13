package com.github.kornilovmikhail.mvpandroidproject.di.event.module

import com.github.kornilovmikhail.mvpandroidproject.data.repository.PaginationPreferencesRepo
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.EventDao
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXAPI
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsDBRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsNetworkRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.EventScope
import dagger.Module
import dagger.Provides

@Module
class EventModule {
    @Provides
    @EventScope
    fun provideEventsRepo(
        eventsDBRepo: EventsDBRepo,
        eventsNetworkRepo: EventsNetworkRepo
    ): EventsRepo = EventsRepo(eventsDBRepo, eventsNetworkRepo, providePagination())

    @Provides
    @EventScope
    fun providePagination(): PaginationPreferencesRepo =
        PaginationPreferencesRepo()

    @Provides
    @EventScope
    fun provideEventsNetworkRepo(spaceXAPI: SpaceXAPI): EventsNetworkRepo =
        EventsNetworkRepo(spaceXAPI)

    @Provides
    @EventScope
    fun provideEventsDBRepo(eventDao: EventDao): EventsDBRepo = EventsDBRepo(eventDao)
}
