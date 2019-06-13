//package com.github.kornilovmikhail.mvpandroidproject.presenter
//
//import com.arellomobile.mvp.InjectViewState
//import com.arellomobile.mvp.MvpPresenter
//import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
//import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksView
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
