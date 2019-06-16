//package com.github.adelina609.stackoverrelations.presenter
//
//import com.arellomobile.mvp.InjectViewState
//import com.arellomobile.mvp.MvpPresenter
//import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
//import com.github.adelina609.stackoverrelations.ui.links.LinksView
//import io.reactivex.rxkotlin.subscribeBy
//
//@InjectViewState
//class LinksPresenter(private val questionsRepo: QuestionsRepo) : MvpPresenter<LinksView>() {
//
//    fun getLinks(position: Int) {
//        questionsRepo.getQuestions(0)
//            .doOnSubscribe {
//                viewState.showProgressBar()
//            }
//            .doAfterTerminate {
//                viewState.hideProgressBar()
//            }
//            .subscribeBy(
//                onSuccess = {
//                    viewState.displayQuestion(it[position])
//                },
//                onError =
//                {
//                    viewState.displayError()
//                }
//            )
//    }
//}
