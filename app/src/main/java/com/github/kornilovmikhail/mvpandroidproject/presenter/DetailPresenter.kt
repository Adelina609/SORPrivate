package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.AnswersRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class DetailPresenter(private val questionsRepo: QuestionsRepo, private val router: Router,
                      private val answersRepo: AnswersRepo) : MvpPresenter<DetailView>() {

    var qId : Long = 0
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
                    qId = it[position].id + 0L
                    viewState.displayQuestion(it[position])
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }

    fun getAnswers(offset: Int) {
        answersRepo.getAnswers(offset, qId)
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
                            println("**************************"+ "IS EMPTY ANSWERS")
                            viewState.detachOnScrollListeners()
                        }
                    } else {
                        println("&&&&&&&&&&&&&&&&&&&&&&&& IN getAnswers")
                        answersRepo.cacheAnswers(it)
                        viewState.displayAnswers(it)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+it.message)
                    viewState.displayError()
                }
            )
    }

    companion object {
        private const val offsetDefault = 0
    }

//    fun onIconClicked(position: Int) {
//        router.navigateTo(Screens.LinksScreen(position))
//    }
}
