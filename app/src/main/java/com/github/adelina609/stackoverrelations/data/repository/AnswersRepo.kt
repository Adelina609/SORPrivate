package com.github.adelina609.stackoverrelations.data.repository

import android.net.Uri
import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question
import io.reactivex.Single
import java.net.URI

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

    private fun getAnswersFromNetwork(offset: Int, id: Long): Single<List<Answer>> {
        return answersNetworkRepo.getAnswers(id)
    }

    fun getCountAnswersByEmail(email : String?) : Single<List<Int>> =
            answersNetworkRepo.getAnswersByEmail(email)

    fun getNewEmptyAnswer() : Single<Answer> =
        answersNetworkRepo.getNewEmptyAnswer()

    fun postNewAnswer(answer:Answer, qId : Long, username: String, uri: String) : Single<Answer>
            = answersNetworkRepo.addNewAnswer(answer, qId, username, uri)

    private fun getAnswersFromDB(): Single<List<Answer>> = answersDBRepo.getAnswers()

    fun cacheAnswers(answers: List<Answer>) {
        answersDBRepo.saveAnswers(answers).subscribe()
    }
}
