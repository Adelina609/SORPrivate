package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.detail.DetailView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class DetailPresenter(private val questionsRepo: QuestionsRepo, private val router: Router,
                      private val answersRepo: AnswersRepo) : MvpPresenter<DetailView>() {

    var qId : Long = 0L
    fun getQuestion(id: Long) {
        questionsRepo.getQuestion(id)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    qId = it.id + 0L
                    viewState.displayQuestion(it)
                    //getAnswers(0)
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }

    fun getAnswers(offset: Int, id : Long) {
        println()
        println("000000000000000000000000 IN DETPRES getAnswers()   " + id)
//        println()
//        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + qId)
        answersRepo.getAnswers(offset, id)
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
                        //answersRepo.cacheAnswers(it)
                        viewState.displayAnswers(it)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+it.message)
                    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + qId)
                    viewState.displayError()
                }
            )
    }

    fun onBackPressed() = router.newRootScreen(Screens.ListScreen())

    companion object {
        private const val offsetDefault = 0
    }

    fun onAnswerBtnClick() = router.navigateTo(Screens.NewAnswerScreen(qId))

//    fun onIconClicked(id: Int) {
//        router.navigateTo(Screens.LinksScreen(id))
//    }
}
