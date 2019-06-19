package com.github.adelina609.stackoverrelations.ui.new_question

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SingleStateStrategy::class)
interface NewQuestionView : MvpView {
    fun sendQuestion()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
}