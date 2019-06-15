package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListView
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
                            println("**************************"+ "IS EMPTY")
                            viewState.detachOnScrollListeners()
                        }
                    } else {
                        questionsRepo.cacheQuestions(it)
                        viewState.displayQuestions(it)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+it.message)
                    viewState.displayError()
                }
            )
    }

    fun initSharedPreferences(sharedPreferences: SharedPreferences) = questionsRepo.setSharedPreferences(sharedPreferences)

    fun setSharedPrefs(value: Int) = questionsRepo.setCurrentPagination(value)

    fun questionClick(position: Int) = router.navigateTo(Screens.DetailScreen(position))

    companion object {
        private const val offsetDefault = 0
    }

}
