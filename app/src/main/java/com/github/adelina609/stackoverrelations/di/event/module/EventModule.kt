package com.github.adelina609.stackoverrelations.di.event.module

import com.github.adelina609.stackoverrelations.data.repository.PaginationPreferencesRepo
import com.github.adelina609.stackoverrelations.data.local.dao.EventDao
import com.github.adelina609.stackoverrelations.data.network.SpaceXAPI
import com.github.adelina609.stackoverrelations.data.repository.EventsDBRepo
import com.github.adelina609.stackoverrelations.data.repository.EventsNetworkRepo
import com.github.adelina609.stackoverrelations.data.repository.EventsRepo
import com.github.adelina609.stackoverrelations.di.event.scope.EventScope
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
