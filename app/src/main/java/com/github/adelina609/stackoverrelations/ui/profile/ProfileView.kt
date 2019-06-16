package com.github.adelina609.stackoverrelations.ui.profile

import com.arellomobile.mvp.MvpView
import com.github.adelina609.stackoverrelations.data.entity.Question

interface ProfileView : MvpView {
    fun displayQuestions(list: List<Question>)
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun detachOnScrollListeners()
    fun setQuestionsValue(questions : Int)
    fun setAnswersValue(answers : Int)

}