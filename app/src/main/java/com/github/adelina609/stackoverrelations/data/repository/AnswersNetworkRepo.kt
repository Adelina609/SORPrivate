package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.network.SorApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AnswersNetworkRepo(private val sorApi: SorApi) {

    fun getAnswers(id : Long) : Single<List<Answer>>  {
        println("5555555555555555555555 IN NETW REPO" + id)
        return sorApi.loadAnswers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAnswersByEmail(email : String) : Int =
            sorApi.getAnswersByEmail(email)

    fun getNewEmptyAnswer() : Single<Answer> =
            sorApi
                .getNewEmptyAnswer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun addNewAnswer(answer: Answer, qId : Long) =
            sorApi
                .addAnswerInQuestion(answer, qId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


}