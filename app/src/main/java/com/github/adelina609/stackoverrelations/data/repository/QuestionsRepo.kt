package com.github.adelina609.stackoverrelations.data.repository

import android.content.SharedPreferences
import com.github.adelina609.stackoverrelations.data.entity.Question
import io.reactivex.Single

class QuestionsRepo(
    private val questionsDBRepo: QuestionsDBRepo,
    private val questionsNetworkRepo: QuestionsNetworkRepo,
    private val preferencesRepo: PreferencesRepo
//    private val answersDBRepo: AnswersDBRepo
) {
    companion object {
        private var isFirst = true
    }

    fun getQuestion(id: Long): Single<Question> =
        getQuestionByIDFromNetw(id)


    fun getQuestions(offset: Int): Single<List<Question>> {
        if (isFirst) {
            isFirst = false
            //offset = 0?
            return getQuestionsFromNetwork(offset)
        }
        if (offset >= 0) {
            return getQuestionsFromNetwork(offset)
        }
        return getQuestionsFromDB()
    }

    private fun getCurrentPagination(): Int? = preferencesRepo.getCurrentPagination()

    fun setCurrentPagination(pagination: Int) {
        preferencesRepo.setCurrentPagination(pagination)
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

//    private fun getAnswersFromNetwork(offset: Int) : Single<List<Answer>> =
//        questionsNetworkRepo.getAnswers()

        private

    fun getQuestionsFromDB(): Single<List<Question>> = questionsDBRepo.getQuestions()

//    private fun getAnswersFromDB(): Single<List<Answer>> = answersDBRepo.getAnswers()

    fun cacheQuestions(questions: List<Question>) {
        questionsDBRepo.saveQuestions(questions).subscribe()
    }

//    fun cacheAnswers(answers : List<Answer>) {
//        answersDBRepo.saveAnswers(answers).subscribe()
//    }
}
