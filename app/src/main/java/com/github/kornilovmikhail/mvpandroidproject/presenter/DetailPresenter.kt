package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class DetailPresenter(private val questionsRepo: QuestionsRepo, private val router: Router) : MvpPresenter<DetailView>() {
    fun getQuestion(position: Int) {
        questionsRepo.getQuestions(0)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    viewState.displayQuestion(it[position])
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }

//    @SuppressLint("CheckResult")
//
//    fun getAnswers(offset: Int) {
//        questionsRepo.getAnswers(offset)
//            .doOnSubscribe {
//                viewState.showProgressBar()
//            }
//            .doAfterTerminate {
//                viewState.hideProgressBar()
//            }
//            .subscribeBy(
//                onSuccess = {
//                    if (it.isEmpty()) {
//                        if (offset != offsetDefault) {
//                            println("**************************"+ "IS EMPTY")
//                            viewState.detachOnScrollListeners()
//                        }
//                    } else {
//                        questionsRepo.cacheAnswers(it)
//                        viewState.displayAnswers(it)
//                        viewState.displaySuccess()
//                    }
//                },
//                onError = {
//                    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+it.message)
//                    viewState.displayError()
//                }
//            )
//    }
//
//    companion object {
//        private const val offsetDefault = 0
//    }

//    fun onIconClicked(position: Int) {
//        router.navigateTo(Screens.LinksScreen(position))
//    }
}
