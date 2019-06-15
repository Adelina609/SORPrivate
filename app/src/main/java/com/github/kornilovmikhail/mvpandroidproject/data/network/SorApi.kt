package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface SorApi {
    @GET("questions")
    fun loadQuestions(): Single<List<Question>>
//
//    @GET("answers")
//    fun loadAnswers(): Single<List<Answer>>
//
//    @POST("questions")
//    fun addQuestion(question: Question)
//
//    @POST("answers")
//    fun addAnswer(answer: Answer)
}