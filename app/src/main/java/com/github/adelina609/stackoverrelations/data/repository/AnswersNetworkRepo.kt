package com.github.adelina609.stackoverrelations.data.repository

import android.net.Uri
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

    fun getAnswersByEmail(email : String?) : Single<List<Int>> =
            sorApi.getAnswersByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getNewEmptyAnswer() : Single<Answer> =
            sorApi
                .getNewEmptyAnswer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun addNewAnswer(answer: Answer, qId : Long, username: String, uri: String) =
            sorApi
                .addAnswerInQuestion(answer, qId, username, uri)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


}