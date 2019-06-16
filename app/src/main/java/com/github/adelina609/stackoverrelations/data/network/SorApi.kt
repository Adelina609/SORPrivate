package com.github.adelina609.stackoverrelations.data.network

import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question
import io.reactivex.Single
import retrofit2.http.*
import retrofit2.http.POST



interface SorApi {
    @GET("questions")
    fun loadQuestions(): Single<List<Question>>

    @GET("questions/{id}")
    fun getQuestionById(@Path("id") id : Long) : Single<Question>

    @GET("questions/{id}/answer")
    fun loadAnswers(@Path("id") id : Long): Single<List<Answer>>

    @GET("questionsg")
    fun getNewEmptyQuestion() : Single<Question>

    @GET("answersg")
    fun getNewEmptyAnswer() : Single<Answer>

    @POST("questions")
    //@FormUrlEncoded
    fun addQuestion(@Body question: Question): Single<Question>

    @POST("questions/{id}")
    fun addAnswerInQuestion(@Body answer: Answer, @Path("id") qId : Long) : Single<Answer>

}