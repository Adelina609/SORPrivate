package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.AnswerDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AnswersDBRepo(private val answerDao: AnswerDao) {

    fun getAnswers(): Single<List<Answer>> =
        answerDao.getAnswers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveAnswers(answers: List<Answer>): Completable =
        Completable.fromAction {
            answerDao.insertAnswers(answers)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}