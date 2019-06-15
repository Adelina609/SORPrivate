package com.github.kornilovmikhail.mvpandroidproject.ui.new_question

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
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.AnswerModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.QuestionModule
import com.github.kornilovmikhail.mvpandroidproject.presenter.NewQuestionPresenter
import kotlinx.android.synthetic.main.fragment_add_new_question.*
import kotlinx.android.synthetic.main.fragment_list.*
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
        var title: String = et_title_fr_add_q.text.toString()
        var description: String = et_describe_fr_add_q.text.toString()

        if (title != null && description != null) {
            //TODO: EMAIL вводить пользователя!!!!!!!!!!!!!
            npresenter.onSendBtn(title, description, "email")
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

    private fun setupViews(){
        fr_add_new_q_progressBar.visibility = ProgressBar.INVISIBLE
        send_button.setOnClickListener(View.OnClickListener {
            sendQuestion()
        })
    }

    override fun displayError() {
        Toast.makeText(context, getString(R.string.server_add_newq_error),
            Toast.LENGTH_SHORT).show()
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