package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.ui.profile.ProfileView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class ProfilePresenter(
    private val questionsRepo: QuestionsRepo, private val router: Router,
    private val answersRepo: AnswersRepo
) : MvpPresenter<ProfileView>() {

    fun getQuestions(email : String) {
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

    fun getAnswers(email : String){
        viewState.setAnswersValue(
            answersRepo.getCountAnswersByEmail(email))
    }





}
