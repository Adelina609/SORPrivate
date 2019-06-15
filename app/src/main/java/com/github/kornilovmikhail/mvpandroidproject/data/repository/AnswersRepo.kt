package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import io.reactivex.Single

class AnswersRepo(
    private val answersDBRepo: AnswersDBRepo,
    private val answersNetworkRepo: AnswersNetworkRepo
) {
    companion object {
        private var isFirst = true
    }

    fun getAnswers(offset: Int, id: Long): Single<List<Answer>> {
        if (isFirst) {
            isFirst = false
            return getAnswersFromNetwork(offset, id)
        }
        if (offset > 0) {
            return getAnswersFromNetwork(offset, id)
        }
        return getAnswersFromDB()
    }

    private fun getAnswersFromNetwork(offset: Int, id: Long): Single<List<Answer>> =
        answersNetworkRepo.getAnswers(id)

    private fun getAnswersFromDB(): Single<List<Answer>> = answersDBRepo.getAnswers()

    fun cacheAnswers(answers: List<Answer>) {
        answersDBRepo.saveAnswers(answers).subscribe()
    }
}
