package com.github.adelina609.stackoverrelations.ui.list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.adelina609.stackoverrelations.data.entity.Question

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ListView : MvpView {
    fun displayQuestions(listQuestions: List<Question>)
    fun displaySuccess()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun detachOnScrollListeners()
}
