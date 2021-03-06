package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.NotificationNetworkRepo
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.notification.NotificationView
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class NotificationPresenter(
    private val notificationNetworkRepo: NotificationNetworkRepo,
    private val router: Router
) : MvpPresenter<NotificationView>() {

    fun getNotifications() {
        val email = FirebaseAuth.getInstance().currentUser?.email
        notificationNetworkRepo.getNotifsByEmail(email)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    println(it.toString())
                    viewState.displayNotifications(it)
                }, onError = {
                    viewState.displayError()
                })
    }

    fun notifClick(id: Int) {
        val idl = id + 0L
        router.navigateTo(Screens.DetailScreen(idl))
    }
}