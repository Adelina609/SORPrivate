package com.github.kornilovmikhail.mvpandroidproject.ui.list

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.di.event.component.DaggerQuestionComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.QuestionModule
import com.github.kornilovmikhail.mvpandroidproject.presenter.ListPresenter
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : MvpAppCompatFragment(), ListView {

    @Inject
    @InjectPresenter
    lateinit var listPresenter: ListPresenter

    @ProvidePresenter
    fun getPresenter(): ListPresenter = listPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        listPresenter.getQuestions(0)
    }

    private fun setupViews() {
        rv_questions.layoutManager = LinearLayoutManager(context)
        rv_questions.addOnScrollListener(OnScrollListener(
            rv_questions.layoutManager as LinearLayoutManager
        ) {
            listPresenter.getQuestions(it)
        })
    }

    override fun displayQuestions(listQuestions: List<Question>) {
        println("**************************" + listQuestions[0])
        if (rv_questions.adapter == null) {
            rv_questions.adapter = QuestionAdapter(listQuestions) {
                listPresenter.questionClick(it)
            }
        }
        (rv_questions.adapter as QuestionAdapter).submitList(listQuestions)
    }

    override fun displaySuccess() {
        Toast.makeText(context, getString(R.string.server_questions_success), Toast.LENGTH_SHORT).show()
    }

    override fun displayError() {
        Toast.makeText(context, getString(R.string.server_questions_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        main_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        main_progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun detachOnScrollListeners() = rv_questions.clearOnScrollListeners()

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_pagination -> createDialog()
//        }
//        return true
//    }

//    private fun createDialog() {
//        val dialog = PaginationDialog()
//        dialog.show(activity?.supportFragmentManager, "custom")
//    }

    companion object {
        fun getInstance(): ListFragment = ListFragment()
    }
}
