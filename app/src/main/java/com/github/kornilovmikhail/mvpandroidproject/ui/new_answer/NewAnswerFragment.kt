package com.github.kornilovmikhail.mvpandroidproject.ui.new_answer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.di.event.component.DaggerQuestionComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.QuestionModule
import com.github.kornilovmikhail.mvpandroidproject.presenter.NewAnswerPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.new_question.NewQuestionView
import kotlinx.android.synthetic.main.fragment_add_new_question.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_new_answer.*
import javax.inject.Inject

class NewAnswerFragment : MvpAppCompatFragment(), NewAnswerView {
    @Inject
    @InjectPresenter
    lateinit var newAnswPresenter: NewAnswerPresenter

    @ProvidePresenter
    fun getPresenter(): NewAnswerPresenter = newAnswPresenter

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
        var answer: String = et_describe_fr_add_answer.text.toString()

        if (answer != null) {
            //TODO: EMAIL вводить пользователя!!!!!!!!!!!!! (на предыдуш скрине)
            newAnswPresenter.onSendBtn(answer, email, idQuestion)
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
        send_button_fr_add_a.setOnClickListener(View.OnClickListener {
            sendAnswer()
        })
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

        fun getInstance(email : String, qId : Long): NewAnswerFragment {
            val newAnswerFragment = NewAnswerFragment()
            val args = Bundle()
            args.putString(EXTRA_EMAIL, email)
            args.putLong(EXTRA_QID, qId)
            newAnswerFragment.arguments = args
            return newAnswerFragment
        }
    }
}