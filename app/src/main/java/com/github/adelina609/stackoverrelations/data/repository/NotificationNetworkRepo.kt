package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.entity.Notification
import com.github.adelina609.stackoverrelations.data.network.SorApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NotificationNetworkRepo(private val sorApi: SorApi) {
    fun getAnswersByEmail(email : String?) : Single<List<Notification>> =
        sorApi.getNotifications(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}