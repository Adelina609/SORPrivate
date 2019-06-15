package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.new_question.NewQuestionView
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

@InjectViewState
class NewQuestionPresenter(private val questionsRepo: QuestionsRepo, private val router: Router)
    : MvpPresenter<NewQuestionView>()  {

    fun onSendBtn(title: String, descr : String, email : String){
        questionsRepo.getNewEmptyQuestion()
             .doOnSubscribe {
                 viewState.showProgressBar()
             }
             .doAfterTerminate {
                 viewState.hideProgressBar()
             }
            .subscribeBy(
                onSuccess = {
                    println("????????????????????????????????????? Question is NULL : "+(it == null))
                    val question = it
                    question.title = title
                    question.description = descr
                    question.email = email
                    println(question)
                    postNewQuestion(question)
                }, onError = {
                    println("????????????????????????????????????? THROWABLE is NULL : "+(it.message))
                    viewState.displayError()
                }

            )
    }

    fun postNewQuestion(question : Question){
        questionsRepo.postNewQuestion(question)
            .doOnSubscribe {
            viewState.showProgressBar()
        }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    println ("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]] IM IN POST" + it.id)
                    router.navigateTo(Screens.ListScreen())
                },
                onError =
                {
                    println ("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]] THROWABLE IS" + it.message)
                    viewState.displayError()
                }
            )
    }

}