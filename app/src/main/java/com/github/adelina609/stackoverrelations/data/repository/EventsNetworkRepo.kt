package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.entity.Event
import com.github.adelina609.stackoverrelations.data.network.SpaceXAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventsNetworkRepo(private val spaceXAPI: SpaceXAPI) {
    fun getEvents(offset: Int, pagination: Int?): Single<List<Event>> =
        spaceXAPI
            .loadEvents(offset, pagination)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
