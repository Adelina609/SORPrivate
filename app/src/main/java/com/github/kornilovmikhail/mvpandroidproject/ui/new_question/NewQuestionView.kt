package com.github.kornilovmikhail.mvpandroidproject.ui.new_question

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NewQuestionView : MvpView {
    fun sendQuestion()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
}