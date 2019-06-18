package com.github.adelina609.stackoverrelations.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.list.ListView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class ListPresenter(private val questionsRepo: QuestionsRepo, private val router: Router) :
    MvpPresenter<ListView>() {

    fun getQuestions(offset: Int) {
        questionsRepo.getQuestions(offset)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        if (offset != offsetDefault) {
                            viewState.detachOnScrollListeners()
                        }
                    } else {
                        viewState.displayQuestions(it)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    viewState.displayError()
                }
            )
    }

    fun initSharedPreferences(sharedPreferences: SharedPreferences) =
        questionsRepo.setSharedPreferences(sharedPreferences)

    fun setSharedPrefs(value: Int) = questionsRepo.setCurrentPagination(value)

    fun questionClick(id: Int) {
        val idl = id + 0L
        router.navigateTo(Screens.DetailScreen(idl))
    }

    fun onBtnClick() = router.navigateTo(Screens.NewQuestionScreen())

    companion object {
        private const val offsetDefault = 0
    }

}
