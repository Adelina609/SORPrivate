package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.network.SorApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionsNetworkRepo(private val sorApi: SorApi) {
    fun getQuestions(): Single<List<Question>> =
        sorApi
            .loadQuestions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

//    fun getAnswers() : Single<List<Answer>> =
//            sorApi.loadAnswers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
//    fun addNewAnswer(answer: Answer) =
//            sorApi.addAnswer(answer)
//
//    fun addNewQuestion(question: Question) =
//            sorApi.addQuestion(question)
}
