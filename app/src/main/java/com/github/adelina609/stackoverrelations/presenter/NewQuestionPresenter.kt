package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.entity.Question
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.new_question.NewQuestionView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class NewQuestionPresenter(private val questionsRepo: QuestionsRepo, private val router: Router) :
    MvpPresenter<NewQuestionView>() {

    fun onSendBtn(title: String, descr: String, email: String?) {
        questionsRepo.getNewEmptyQuestion()
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    val question = it
                    question.title = title
                    question.description = descr
                    question.email = email.toString()
                    println(question)
                    postNewQuestion(question)
                }, onError = {
                    viewState.displayError()
                }

            )
    }

    fun postNewQuestion(question: Question) {
        questionsRepo.postNewQuestion(question)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    router.backTo(Screens.ListScreen())
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }

}