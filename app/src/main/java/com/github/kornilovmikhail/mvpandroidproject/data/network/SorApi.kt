package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SorApi {
    @GET("questions")
    fun loadQuestions(): Single<List<Question>>

    @GET("questions/{id}/answers")
    fun loadAnswers(@Path("id") id : Long): Single<List<Answer>>

    @POST("questions")
    fun addQuestion(question: Question)

    @POST("answers")
    fun addAnswer(answer: Answer)
}