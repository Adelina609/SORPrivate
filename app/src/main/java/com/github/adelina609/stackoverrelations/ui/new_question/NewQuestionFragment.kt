package com.github.adelina609.stackoverrelations.ui.new_question

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
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.presenter.NewQuestionPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_add_new_question.*
import javax.inject.Inject

class NewQuestionFragment : MvpAppCompatFragment(), NewQuestionView {
    @Inject
    @InjectPresenter
    lateinit var npresenter: NewQuestionPresenter

    @ProvidePresenter
    fun getPresenter(): NewQuestionPresenter = npresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun sendQuestion() {
        val title: String = et_title_fr_add_q.text.toString()
        val description: String = et_describe_fr_add_q.text.toString()

        if (title != null && description != null) {
            npresenter.onSendBtn(title, description, FirebaseAuth.getInstance().currentUser?.email)
        } else {
            Toast.makeText(context, getString(R.string.emptyTitleOrDesc), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_new_question, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        fr_add_new_q_progressBar.visibility = ProgressBar.INVISIBLE
        send_button.setOnClickListener {
            sendQuestion()
        }
    }

    override fun displayError() {
        Toast.makeText(
            context, getString(R.string.server_add_newq_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showProgressBar() {
        fr_add_new_q_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        fr_add_new_q_progressBar.visibility = ProgressBar.INVISIBLE
    }

    companion object {
        fun getInstance(): NewQuestionFragment = NewQuestionFragment()
    }

}