package com.github.adelina609.stackoverrelations.data.repository

import android.content.SharedPreferences
import com.github.adelina609.stackoverrelations.data.entity.Question
import io.reactivex.Single

class QuestionsRepo(
    private val questionsNetworkRepo: QuestionsNetworkRepo,
    private val preferencesRepo: PreferencesRepo){

    fun getQuestion(id: Long): Single<Question> =
        getQuestionByIDFromNetw(id)

    fun getQuestions(offset: Int): Single<List<Question>> {
            return getQuestionsFromNetwork(offset)
    }


    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        preferencesRepo.setSharedPrefs(sharedPreferences)
    }

    private fun getQuestionsFromNetwork(offset: Int): Single<List<Question>> =
        questionsNetworkRepo.getQuestions()

    private fun getQuestionByIDFromNetw(id: Long): Single<Question> =
        questionsNetworkRepo.getQuestionById(id)

    fun postNewQuestion(question: Question) =
        questionsNetworkRepo.addNewQuestion(question)

    fun getNewEmptyQuestion(): Single<Question> =
        questionsNetworkRepo.getNewEmptyQuestion()

    fun getQuestionsByEmail(email: String?): Single<List<Question>> =
        questionsNetworkRepo.getQuestionsByEmail(email)

    fun setCurrentPagination(pagination: Int) {
        preferencesRepo.setCurrentPagination(pagination)
    }
    companion object {
        private var isFirst = true
    }
}
