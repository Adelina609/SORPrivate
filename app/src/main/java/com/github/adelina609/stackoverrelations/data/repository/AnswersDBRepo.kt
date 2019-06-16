package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.local.dao.AnswerDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AnswersDBRepo(private val answerDao: AnswerDao) {

    fun getAnswers(): Single<List<Answer>> =
        answerDao.getAnswers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveAnswers(answers: List<Answer>): Completable {
        return Completable.fromAction {
            answerDao.insertAnswers(answers)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}