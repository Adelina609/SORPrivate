package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.local.dao.EventDao
import com.github.adelina609.stackoverrelations.data.entity.Event
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventsDBRepo(private val eventDao: EventDao) {

    fun getEvents(): Single<List<Event>> =
        eventDao.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveEvents(events: List<Event>): Completable =
        Completable.fromAction {
            eventDao.insertEvents(events)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}
