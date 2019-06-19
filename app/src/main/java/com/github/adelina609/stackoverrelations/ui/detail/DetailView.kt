package com.github.adelina609.stackoverrelations.ui.detail


import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun displaySuccess()
    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun displayError()
    
    fun displayQuestion(question: Question)
    fun showProgressBar()
    fun hideProgressBar()
    fun detachOnScrollListeners()
    fun displayAnswers(listAnswer: List<Answer>)
    fun hideAnswers()
}
