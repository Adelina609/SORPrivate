package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.QuestionDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionsDBRepo(private val questionDao: QuestionDao) {

    fun getQuestions(): Single<List<Question>> =
        questionDao.getQuestions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveQuestions(questions: List<Question>): Completable =
        Completable.fromAction {
            questionDao.insertQuestions(questions)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}
