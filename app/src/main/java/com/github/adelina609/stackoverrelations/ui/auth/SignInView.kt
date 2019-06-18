package com.github.adelina609.stackoverrelations.ui.auth

import com.arellomobile.mvp.MvpView
import com.google.firebase.auth.FirebaseUser

interface SignInView : MvpView {
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun updateUI(user : FirebaseUser?)
}