package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.repository.AnswersRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.new_answer.NewAnswerView
import com.github.kornilovmikhail.mvpandroidproject.ui.new_question.NewQuestionView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class NewAnswerPresenter(private val answersRepo: AnswersRepo, private val router: Router)
    : MvpPresenter<NewAnswerView>()  {

    fun onSendBtn(answer : String, email : String, qId : Long){
        answersRepo.getNewEmptyAnswer()
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    println("????????????????????????????????????? Answer is NULL : "+(it == null))
                    var answerIt = it
                    answerIt.answer = answer
                    answerIt.email = email
                    answerIt.question_id = qId.toInt()
                    println(answerIt)
                    postNewAnswer(answerIt)
                }, onError = {
                    println("????????????????????????????????????? THROWABLE in Answers is NULL : "+(it.message))
                    viewState.displayError()
                }

            )
    }

    fun postNewAnswer(answer : Answer){
        answersRepo.postNewAnswer(answer, answer.question_id + 0L)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    println ("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]] IM IN POST ANSWER" + it.id)
                    router.navigateTo(Screens.DetailScreen(answer.question_id+0L))
                },
                onError =
                {
                    println ("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]] THROWABLE IS IN POST ANSWER" + it.message)
                    viewState.displayError()
                }
            )
    }

}