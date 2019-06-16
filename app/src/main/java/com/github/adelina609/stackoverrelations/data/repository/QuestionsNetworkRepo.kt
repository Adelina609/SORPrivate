package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question
import com.github.adelina609.stackoverrelations.data.network.SorApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionsNetworkRepo(private val sorApi: SorApi) {
    fun getQuestions(): Single<List<Question>> =
        sorApi
            .loadQuestions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNewEmptyQuestion() : Single<Question> =
            sorApi
                .getNewEmptyQuestion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getQuestionsByEmail(email : String) :Single<List<Question>> =
        sorApi.getQuestionsByEmail(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addNewQuestion(question: Question) =
        sorApi
            .addQuestion(question)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getQuestionById(id : Long) =
            sorApi
                .getQuestionById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
}
