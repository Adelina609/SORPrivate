package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.network.SorApi
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
    fun addNewAnswer(answer: Answer) =
            sorApi.addAnswer(answer)
}