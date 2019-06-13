package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXAPI
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
