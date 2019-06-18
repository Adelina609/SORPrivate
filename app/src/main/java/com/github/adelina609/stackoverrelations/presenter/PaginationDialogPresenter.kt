package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.PreferencesRepo
import com.github.adelina609.stackoverrelations.ui.list.dialog.PaginationDialogView

@InjectViewState
class PaginationDialogPresenter(private val preferencesRepo: PreferencesRepo) :
    MvpPresenter<PaginationDialogView>() {

    fun setPagination(paginationValue: Int) {
        preferencesRepo.setCurrentPagination(paginationValue)
    }

    fun dismiss() {
        viewState.hideDialog()
    }
}
