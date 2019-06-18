package com.github.adelina609.stackoverrelations.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener, SignUpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)

        btn_register.setOnClickListener(this)
        btn_to_signin.setOnClickListener(this)

        initFireBaseAuth()
    }

    private lateinit var auth: FirebaseAuth

    fun initFireBaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    fun currentUser() {
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun createAccount(email: String, password: String, validateForm: Boolean) {
        //Log.d(SignUpActivity.TAG, "createAccount:$email")
        if (!validateForm) {
            return
        }

        showProgressBar()

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(SignUpActivity.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(SignUpActivity.TAG, "createUserWithEmail:failure", task.exception)

                    updateUI(null)
                }

                // [START_EXCLUDE]
                hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]
    }

    override fun onStart() {
        super.onStart()
        currentUser()
    }

    fun validateForm(): Boolean {
        var valid = true

        val email = field_email_signup.text.toString()
        if (TextUtils.isEmpty(email)) {
            field_email_signup.error = "Required."
            valid = false
        } else {
            field_email_signup.error = null
        }

        val password = field_password_signup.text.toString()
        if (TextUtils.isEmpty(password)) {
            field_password_signup.error = "Required."
            valid = false
        } else {
            field_password_signup.error = null
        }

        return valid
    }

    override fun updateUI(user: FirebaseUser?) {
        hideProgressBar()
        if (user != null) {
            val intent = MainActivity.newIntent(this, user)
            startActivity(intent)
        } else {
            displayError()
        }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.btn_register -> createAccount(
                field_email_signup.text.toString(),
                field_password_signup.text.toString(), validateForm()
            )
            R.id.btn_to_signin -> startActivity(
                SignInActivity.newIntent(
                    this
                )
            )
        }
    }


    override fun showProgressBar() {
        progressBar_sign_up.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar_sign_up.visibility = ProgressBar.INVISIBLE
    }

    override fun displayError() {
        Toast.makeText(
            baseContext, "Authentication failed.",
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        private const val TAG = "SignUp"

        fun newIntent(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java)
        }
    }
}
