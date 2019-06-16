package com.github.adelina609.stackoverrelations.presenter

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import jdk.nashorn.internal.runtime.ECMAException.getException
import com.google.firebase.auth.FirebaseUser
import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.ui.Screens
import com.github.adelina609.stackoverrelations.ui.sign_in_up.SignInView
import com.google.android.gms.tasks.Task
import ru.terrakok.cicerone.Router


@InjectViewState
class SignInPresenter(private val router: Router) : MvpPresenter<SignInView>(){

    private lateinit var auth: FirebaseAuth

    fun initFireBaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    fun currentUser(){
        val currentUser = auth.currentUser
        viewState.updateUI(currentUser)
    }

    fun signIn(email : String, password : String, ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        viewState.updateUI(user)
                    } else {
                        // If sign in fails, display a message to the use
                        viewState.displayError()
                    }

                    // ...
                })
    }

    //TODO: email в фрагмент передать
    fun goToFeed(email : String?) = router.navigateTo(Screens.ListScreen())

    fun goToSignUp() = router.navigateTo(Screens.SignUpScreen())



}

private fun <TResult> Task<TResult>.addOnCompleteListener(
    signInPresenter: SignInPresenter,
    onCompleteListener: OnCompleteListener<TResult>
) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
