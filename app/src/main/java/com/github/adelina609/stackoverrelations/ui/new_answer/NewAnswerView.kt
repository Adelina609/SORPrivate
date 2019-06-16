package com.github.adelina609.stackoverrelations.ui.new_answer

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SingleStateStrategy::class)
interface NewAnswerView : MvpView {
    fun sendAnswer()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
}