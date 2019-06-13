package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import io.reactivex.Single
import retrofit2.http.GET

interface SorApi {
    @GET("questions")
    fun loadQuestions(): Single<List<Question>>
}