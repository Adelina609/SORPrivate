package com.github.adelina609.stackoverrelations.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.auth.SignInView
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import ru.terrakok.cicerone.Router


@InjectViewState
class SignInPresenter(private val router: Router) : MvpPresenter<SignInView>() {

    private lateinit var auth: FirebaseAuth

    fun initFireBaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    fun currentUser() {
        val currentUser = auth.currentUser
        viewState.updateUI(currentUser)
    }

    fun signIn(email: String, password: String, validateForm: Boolean) {
        //Log.d(SignInActivity.TAG, "signIn:$email")
        if (!validateForm) {
            return
        }

        viewState.showProgressBar()

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(SignInActivity.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    viewState.updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(SignInActivity.TAG, "signInWithEmail:failure", task.exception)
                    viewState.displayError()
                    viewState.updateUI(null)
                }

                // [START_EXCLUDE]
                if (!task.isSuccessful) {
                    //status.setText(R.string.auth_failed)
                }
                viewState.hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }

    fun signInWithCredentials(credential: AuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithCredential:success")
                    val user = auth.currentUser
                    viewState.updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithCredential:failure", task.exception)
                    viewState.displayError()
                    //Toast.makeText(this,"Auth Failed",Toast.LENGTH_LONG).show()
                    viewState.updateUI(null)
                }

                // ...
            }
    }



    //TODO: email в фрагмент передать
    fun goToFeed(email: String?) = router.navigateTo(Screens.ListScreen())

    //fun goToSignUp() = router.navigateTo(Screens.SignUpScreen())
}
