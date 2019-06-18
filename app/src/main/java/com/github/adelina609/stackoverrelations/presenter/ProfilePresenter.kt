package com.github.adelina609.stackoverrelations.presenter

import android.content.SharedPreferences
import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.profile.ProfileView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class ProfilePresenter(
    private val questionsRepo: QuestionsRepo, private val router: Router,
    private val answersRepo: AnswersRepo, private val sharedPreferences: SharedPreferences
) : MvpPresenter<ProfileView>() {

    private val STATUS = "status"
    private val USERNAME = "username"

    fun getQuestions(email : String?) {
        questionsRepo.getQuestionsByEmail(email)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    viewState.setQuestionsValue(it.size)
                    viewState.displayQuestions(it)
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }

    fun getAnswers(email : String?){
            answersRepo.
                getCountAnswersByEmail(email)
                .doOnSubscribe {
                    viewState.showProgressBar()
                }
                .doAfterTerminate {
                    viewState.hideProgressBar()
                }
                .subscribeBy(
                    onSuccess = {
                        println("2222222222222222222222222 + getAnsCount profilepres")
                        viewState.setAnswersValue(it.size)
                    },
                    onError =
                    {
                        println("2222222222222222222222222 + getAnsCount profilepres" + it.message)
                        viewState.displayError()
                    }
                )
    }

    fun setUp(){
        viewState.setTexts(sharedPreferences.getString(USERNAME, USERNAME), sharedPreferences.getString(STATUS, STATUS))
    }

    fun putPhoto(uri : Uri?){
        sharedPreferences.edit().putString("photo", uri.toString()).apply()
    }
    fun goToSettings(){
        router.navigateTo(Screens.SharedPreferenceScreen())
    }

    fun questionClick(id: Int) {
        val idl = id + 0L
        router.navigateTo(Screens.DetailScreen(idl))
    }
}
