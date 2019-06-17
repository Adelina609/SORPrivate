package com.github.adelina609.stackoverrelations.ui.sign_in_up

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.presenter.SignInPresenter
import com.github.adelina609.stackoverrelations.ui.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activty_sign_in.*
import javax.inject.Inject

class SignInActivity : AppCompatActivity(), View.OnClickListener, SignInView {

    private val RC_SIGN_IN = 7
    //Google Sign In Client
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var auth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_sign_in)

        auth = FirebaseAuth.getInstance()
        // Buttons
        btn_login.setOnClickListener(this)
        btn_sign_in_with_google.setOnClickListener(this)
        btn_to_signup.setOnClickListener(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)


        // [START initialize_auth]
        // Initialize Firebase Auth
        // [END initialize_auth]
    }



    fun signIn(email: String, password: String) {
        //Log.d(SignInActivity.TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }

        showProgressBar()

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(SignInActivity.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(SignInActivity.TAG, "signInWithEmail:failure", task.exception)
                    displayError()
                    updateUI(null)
                }

                // [START_EXCLUDE]
                if (!task.isSuccessful) {
                    //status.setText(R.string.auth_failed)
                }
                hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }



    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun validateForm(): Boolean {
        var valid = true

        val email = field_email.text.toString()
        if (TextUtils.isEmpty(email)) {
            field_email.error = "Required."
            valid = false
        } else {
            field_email.error = null
        }

        val password = field_password.text.toString()
        if (TextUtils.isEmpty(password)) {
            field_password.error = "Required."
            valid = false
        } else {
            field_password.error = null
        }

        return valid
    }

    override fun updateUI(user: FirebaseUser?) {
        hideProgressBar()
        if (user != null) {
            val intent = MainActivity.newIntent(this, user)
            startActivity(intent)
        } else {

            Toast.makeText(this, "user is null", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Login", "Google sign in failed", e)
                // ...
            }

        }
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        Log.d("Login", "firebaseAuthWithGoogle:" + acct?.id)

        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this,"Auth Failed",Toast.LENGTH_LONG).show()
                    updateUI(null)
                }

                // ...
            }
    }

    override fun showProgressBar() {
        progressBar_sign_in.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar_sign_in.visibility = ProgressBar.INVISIBLE
    }

    override fun displayError(){
        Toast.makeText(
            baseContext, "Authentication failed.",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.btn_to_signup -> startActivity(
                SignUpActivity.newIntent(
                    this
                )
            )
            R.id.btn_login -> signIn(field_email.text.toString(), field_password.text.toString())
            R.id.btn_sign_in_with_google -> startActivityForResult(mGoogleSignInClient.signInIntent, RC_SIGN_IN)
        }
    }

    companion object {
        private const val TAG = "SignIn"

        fun newIntent(context: Context?) : Intent{
            return Intent(context, SignInActivity::class.java)
        }
    }
}