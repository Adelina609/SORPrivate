package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.PaginationPreferencesRepo
import com.github.adelina609.stackoverrelations.ui.list.dialog.PaginationDialogView

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
