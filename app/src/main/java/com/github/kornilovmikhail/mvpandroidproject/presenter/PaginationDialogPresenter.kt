package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.PaginationPreferencesRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.list.dialog.PaginationDialogView

@InjectViewState
class PaginationDialogPresenter(private val paginationPreferencesRepo: PaginationPreferencesRepo) :
    MvpPresenter<PaginationDialogView>() {

    fun setPagination(paginationValue: Int) {
        paginationPreferencesRepo.setCurrentPagination(paginationValue)
    }

    fun dismiss() {
        viewState.hideDialog()
    }
}
