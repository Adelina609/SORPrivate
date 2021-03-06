package com.github.adelina609.stackoverrelations.ui.new_answer

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
import com.github.adelina609.stackoverrelations.presenter.NewAnswerPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_new_answer.*
import javax.inject.Inject

class NewAnswerFragment : MvpAppCompatFragment(), NewAnswerView {
    @Inject
    @InjectPresenter
    lateinit var newAnswerPresenter: NewAnswerPresenter

    @ProvidePresenter
    fun getPresenter(): NewAnswerPresenter = newAnswerPresenter

    private var email : String = ""
    private var idQuestion = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)

        email = arguments?.getString(EXTRA_EMAIL) ?: DEFAULT_EMAIL
        idQuestion = arguments?.getLong(EXTRA_QID) ?: DEFAULT_QID

        super.onCreate(savedInstanceState)
    }

    override fun sendAnswer() {
        val answer: String = et_describe_fr_add_answer.text.toString()

        if (answer != null) {
            newAnswerPresenter.onSendBtn(answer,
                FirebaseAuth.getInstance().currentUser?.email, idQuestion)
        } else {
            Toast.makeText(context, getString(R.string.emptyDesc), Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_answer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews(){
        fr_add_new_answer_progressBar.visibility = ProgressBar.INVISIBLE
        send_button_fr_add_a.setOnClickListener {
            sendAnswer()
        }
    }

    override fun displayError() {
        Toast.makeText(context, R.string.server_add_newa_error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        fr_add_new_answer_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        fr_add_new_answer_progressBar.visibility = ProgressBar.INVISIBLE
    }
    companion object {
        private const val DEFAULT_EMAIL = "adelina@gmail.com"
        private const val EXTRA_EMAIL= "EMAIL"
        private const val DEFAULT_QID = 0L
        private const val EXTRA_QID = "ID"

        fun getInstance( qId : Long): NewAnswerFragment {
            val newAnswerFragment = NewAnswerFragment()
            val args = Bundle()
            args.putLong(EXTRA_QID, qId)
            newAnswerFragment.arguments = args
            return newAnswerFragment
        }
    }
}