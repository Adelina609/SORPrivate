package com.github.adelina609.stackoverrelations.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.ui.sign_in_up.SignUpView
import com.google.firebase.auth.FirebaseAuth

@InjectViewState
class SignUpPresenter : MvpPresenter<SignUpView>() {

    private lateinit var auth: FirebaseAuth

    fun initFireBaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    fun currentUser() {
        val currentUser = auth.currentUser
        viewState.updateUI(currentUser)
    }

    fun createAccount(email: String, password: String, validateForm: Boolean) {
        //Log.d(SignUpActivity.TAG, "createAccount:$email")
        if (!validateForm) {
            return
        }

        viewState.showProgressBar()

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(SignUpActivity.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    viewState.updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(SignUpActivity.TAG, "createUserWithEmail:failure", task.exception)

                    viewState.updateUI(null)
                }

                // [START_EXCLUDE]
                viewState.hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]
    }
}