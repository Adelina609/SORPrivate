package com.github.kornilovmikhail.mvpandroidproject.ui.list.dialog

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface PaginationDialogView : MvpView {
    fun hideDialog()
}
