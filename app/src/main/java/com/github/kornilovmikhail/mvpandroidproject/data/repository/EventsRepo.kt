package com.github.kornilovmikhail.mvpandroidproject.data.repository

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single

class EventsRepo(
    private val eventsDBRepo: EventsDBRepo,
    private val eventsNetworkRepo: EventsNetworkRepo,
    private val paginationPreferencesRepo: PaginationPreferencesRepo
) {
    companion object {
        private var isFirst = true
    }

    fun getEvents(offset: Int): Single<List<Event>> {
        if (isFirst) {
            isFirst = false
            return getEventsFromNetwork(offset)
        }
        if (offset > 0) {
            return getEventsFromNetwork(offset)
        }
        return getEventsFromDB()
    }

    private fun getCurrentPagination(): Int? = paginationPreferencesRepo.getCurrentPagination()

    fun setCurrentPagination(pagination: Int) {
        paginationPreferencesRepo.setCurrentPagination(pagination)
    }

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        paginationPreferencesRepo.setSharedPrefs(sharedPreferences)
    }

    private fun getEventsFromNetwork(offset: Int): Single<List<Event>> =
        eventsNetworkRepo.getEvents(offset, getCurrentPagination())

    private fun getEventsFromDB(): Single<List<Event>> = eventsDBRepo.getEvents()

    fun cacheEvents(events: List<Event>) {
        eventsDBRepo.saveEvents(events).subscribe()
    }
}
