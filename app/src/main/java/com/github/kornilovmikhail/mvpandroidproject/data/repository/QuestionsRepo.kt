package com.github.kornilovmikhail.mvpandroidproject.data.repository

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import io.reactivex.Single

class QuestionsRepo(
    private val questionsDBRepo: QuestionsDBRepo,
    private val questionsNetworkRepo: QuestionsNetworkRepo,
    private val paginationPreferencesRepo: PaginationPreferencesRepo
) {
    companion object {
        private var isFirst = true
    }

    fun getQuestions(offset: Int): Single<List<Question>> {
        if (isFirst) {
            isFirst = false
            return getQuestionsFromNetwork(offset)
        }
        if (offset > 0) {
            return getQuestionsFromNetwork(offset)
        }
        return getQuestionsFromDB()
    }

    private fun getCurrentPagination(): Int? = paginationPreferencesRepo.getCurrentPagination()

    fun setCurrentPagination(pagination: Int) {
        paginationPreferencesRepo.setCurrentPagination(pagination)
    }

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        paginationPreferencesRepo.setSharedPrefs(sharedPreferences)
    }

    private fun getQuestionsFromNetwork(offset: Int): Single<List<Question>> {
    return questionsNetworkRepo.getQuestions()
    }
    private fun getQuestionsFromDB(): Single<List<Question>> = questionsDBRepo.getQuestions()

    fun cacheQuestions(questions: List<Question>) {
        questionsDBRepo.saveQuestions(questions).subscribe()
    }
}
