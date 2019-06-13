package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXAPI {
    @GET("history")
    fun loadEvents(@Query("offset") offset: Int, @Query("limit") limit: Int?): Single<List<Event>>
}
