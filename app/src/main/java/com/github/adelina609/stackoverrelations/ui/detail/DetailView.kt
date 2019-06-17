package com.github.adelina609.stackoverrelations.ui.detail


import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question

@StateStrategyType(value = SingleStateStrategy::class)
interface DetailView : MvpView {
    fun displayQuestion(question: Question)
    fun displaySuccess()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun detachOnScrollListeners()
    fun displayAnswers(listAnswer: List<Answer>)
}
