package com.github.adelina609.stackoverrelations.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.new_answer.NewAnswerView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class NewAnswerPresenter(
    private val answersRepo: AnswersRepo, private val router: Router,
    private val sharedPreferences: SharedPreferences
) : MvpPresenter<NewAnswerView>() {

    private val username = sharedPreferences.getString("username", "Username")
    private val photo = sharedPreferences.getString("photo", R.drawable.icon_person.toString())

    fun onSendBtn(answer: String, email: String?, qId: Long) {
        answersRepo.getNewEmptyAnswer()
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    var answerIt = it
                    answerIt.answer = answer
                    answerIt.email = email.toString()
                    answerIt.question_id = qId.toInt()
                    postNewAnswer(answerIt)
                }, onError = {
                    viewState.displayError()
                }

            )
    }

    fun postNewAnswer(answer: Answer) {
        answersRepo.postNewAnswer(answer, answer.question_id + 0L, username, photo)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    router.backTo(Screens.DetailScreen(answer.question_id + 0L))
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }
}