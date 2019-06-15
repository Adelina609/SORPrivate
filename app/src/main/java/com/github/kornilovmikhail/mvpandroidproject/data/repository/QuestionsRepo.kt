package com.github.kornilovmikhail.mvpandroidproject.data.repository

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import io.reactivex.Single

class QuestionsRepo(
    private val questionsDBRepo: QuestionsDBRepo,
    private val questionsNetworkRepo: QuestionsNetworkRepo,
    private val paginationPreferencesRepo: PaginationPreferencesRepo
//    private val answersDBRepo: AnswersDBRepo
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

//    fun getAnswers(offset: Int): Single<List<Answer>> {
//        if (isFirst) {
//            isFirst = false
//            return getAnswersFromNetwork(offset)
//        }
//        if (offset > 0) {
//            return getAnswersFromNetwork(offset)
//        }
//        return getAnswersFromDB()
//    }

    private fun getCurrentPagination(): Int? = paginationPreferencesRepo.getCurrentPagination()

    fun setCurrentPagination(pagination: Int) {
        paginationPreferencesRepo.setCurrentPagination(pagination)
    }

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        paginationPreferencesRepo.setSharedPrefs(sharedPreferences)
    }

    private fun getQuestionsFromNetwork(offset: Int): Single<List<Question>> =
        questionsNetworkRepo.getQuestions()

//    private fun getAnswersFromNetwork(offset: Int) : Single<List<Answer>> =
//        questionsNetworkRepo.getAnswers()

    private fun getQuestionsFromDB(): Single<List<Question>> = questionsDBRepo.getQuestions()

//    private fun getAnswersFromDB(): Single<List<Answer>> = answersDBRepo.getAnswers()

    fun cacheQuestions(questions: List<Question>) {
        questionsDBRepo.saveQuestions(questions).subscribe()
    }

//    fun cacheAnswers(answers : List<Answer>) {
//        answersDBRepo.saveAnswers(answers).subscribe()
//    }
}
