package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.di.event.component.DaggerQuestionComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.QuestionModule
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : MvpAppCompatFragment(), DetailView {
    @Inject
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    private var position = 0

    @ProvidePresenter
    fun getPresenter(): DetailPresenter = detailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        position = arguments?.getInt(EXTRA_POSITION) ?: DEFAULT_POSITION
        //setHasOptionsMenu(true)
        detailPresenter.getQuestion(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_open_links -> detailPresenter.onIconClicked(position)
//        }
//        return true
//    }

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

    companion object {
        private const val DEFAULT_POSITION = 0
        private const val EXTRA_POSITION = "POSITION"

        fun getInstance(position: Int): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val args = Bundle()
            args.putInt(EXTRA_POSITION, position)
            detailsFragment.arguments = args
            return detailsFragment
        }
    }
}
