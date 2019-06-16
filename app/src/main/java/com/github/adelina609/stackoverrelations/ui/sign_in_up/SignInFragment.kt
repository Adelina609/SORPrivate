package com.itis.android.firebasesimple.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.event.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.event.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.event.module.QuestionModule
import com.github.adelina609.stackoverrelations.presenter.SignInPresenter
import com.github.adelina609.stackoverrelations.ui.new_question.NewQuestionFragment
import com.github.adelina609.stackoverrelations.ui.sign_in_up.SignInView
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : MvpAppCompatFragment(), SignInView {

    @Inject
    @InjectPresenter
    lateinit var signInPresenter: SignInPresenter

    @ProvidePresenter
    fun getPresenter(): SignInPresenter = signInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        signInPresenter.initFireBaseAuth()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sign_in, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews(){
        hideProgressBar()
        btn_login.setOnClickListener(View.OnClickListener {
            //TODO
            signInPresenter.signIn()
        })
        btn_sign_in_with_google.setOnClickListener(View.OnClickListener {
            //with google api
        })
        btn_to_signup.setOnClickListener(View.OnClickListener {
            signInPresenter.goToSignUp()
        })
    }

    fun goToNext(){

    }

    override fun updateUI(user : FirebaseUser?){
        if (user != null){
            signInPresenter.goToFeed(user.email)
        } else{

        }
    }

    override fun displayError() {
        Toast.makeText(context, getString(R.string.error_signin),
            Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar_sign_in.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar_sign_in.visibility = ProgressBar.INVISIBLE
    }

    companion object {
        fun getInstance(): NewQuestionFragment = NewQuestionFragment()
    }


}
