package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.ui.MainView
import com.github.adelina609.stackoverrelations.ui.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    fun goToFeed() = router.backTo(Screens.ListScreen())

    fun goToProfile() = router.navigateTo(Screens.ProfileScreen())

    fun goToNotifications() = router.navigateTo(Screens.NotificationsScreen())

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(Screens.ListScreen())
    }
}
