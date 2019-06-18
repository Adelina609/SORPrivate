package com.github.adelina609.stackoverrelations.ui.notification

import com.arellomobile.mvp.MvpView
import com.github.adelina609.stackoverrelations.data.entity.Notification

interface NotificationView : MvpView{
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun displayNotifications(list :List<Notification>)
}