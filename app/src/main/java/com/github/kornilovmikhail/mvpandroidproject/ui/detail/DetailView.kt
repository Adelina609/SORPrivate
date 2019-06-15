package com.github.kornilovmikhail.mvpandroidproject.ui.detail


import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    fun displayQuestion(question: Question)
    fun displaySuccess()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun detachOnScrollListeners()
    fun displayAnswers(listAnswer: List<Answer>)
}
