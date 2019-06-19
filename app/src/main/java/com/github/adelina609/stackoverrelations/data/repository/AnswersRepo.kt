package com.github.adelina609.stackoverrelations.data.repository

import com.github.adelina609.stackoverrelations.data.entity.Answer
import io.reactivex.Single

class AnswersRepo(
    private val answersDBRepo: AnswersDBRepo,
    private val answersNetworkRepo: AnswersNetworkRepo
) {

    fun getAnswers(offset: Int, id: Long): Single<List<Answer>> {
        return getAnswersFromNetwork(offset, id)
    }

    private fun getAnswersFromNetwork(offset: Int, id: Long): Single<List<Answer>> {
        return answersNetworkRepo.getAnswers(id)
    }

    fun getCountAnswersByEmail(email: String?): Single<List<Int>> =
        answersNetworkRepo.getAnswersByEmail(email)

    fun getNewEmptyAnswer(): Single<Answer> =
        answersNetworkRepo.getNewEmptyAnswer()

    fun postNewAnswer(answer: Answer, qId: Long, username: String, uri: String): Single<Answer> =
        answersNetworkRepo.addNewAnswer(answer, qId, username, uri)
}
