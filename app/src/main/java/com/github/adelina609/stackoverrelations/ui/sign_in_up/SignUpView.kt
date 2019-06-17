package com.github.adelina609.stackoverrelations.ui.sign_in_up

import com.arellomobile.mvp.MvpView
import com.google.firebase.auth.FirebaseUser

interface SignUpView : MvpView{

    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun updateUI(user : FirebaseUser?)

}