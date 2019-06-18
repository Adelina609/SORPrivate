package com.github.adelina609.stackoverrelations.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.AnswerModule
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.presenter.DetailPresenter
import com.github.adelina609.stackoverrelations.ui.list.OnScrollListener
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class DetailsFragment : MvpAppCompatFragment(), DetailView {
    @Inject
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    private var idQuestion = 0L

    @ProvidePresenter
    fun getPresenter(): DetailPresenter = detailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .answerModule(AnswerModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        idQuestion = arguments?.getLong(EXTRA_ID) ?: DEFAULT_ID
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailPresenter.getQuestion(idQuestion)
        detailPresenter.getAnswers(0, idQuestion)
        setupViews()
    }

    private fun setupViews() {
        rv_answers.layoutManager = LinearLayoutManager(context)
        rv_answers.addOnScrollListener(OnScrollListener(
            rv_answers.layoutManager as LinearLayoutManager
        ) {
            detailPresenter.getAnswers(it, idQuestion)
        })
        btn_answer.setOnClickListener {
            onAnswerBtnClick()
        }
    }

    private fun onAnswerBtnClick() {
        detailPresenter.onAnswerBtnClick()
    }


    override fun displayAnswers(listAnswer: List<Answer>) {
        if (rv_answers.adapter == null) {
            rv_answers.adapter = AnswerAdapter(listAnswer) {
            }
        }
        (rv_answers.adapter as AnswerAdapter).submitList(listAnswer)
    }

    override fun displayQuestion(question: Question) {
        tv_title_details_activity.text = question.title
        tv_details_details_activity.text = question.description
    }

    override fun displayError() {
        Toast.makeText(context, R.string.server_questions_error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        details_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        details_progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun displaySuccess() {
        Toast.makeText(context, getString(R.string.server_questions_success), Toast.LENGTH_SHORT).show()
    }

    override fun detachOnScrollListeners() = rv_questions.clearOnScrollListeners()

    companion object {
        private const val DEFAULT_ID = 0L
        private const val EXTRA_ID = "POSITION"

        fun getInstance(id: Long): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val args = Bundle()
            args.putLong(EXTRA_ID, id)
            detailsFragment.arguments = args
            return detailsFragment
        }
    }
}
