package com.github.adelina609.stackoverrelations.ui.profile

import android.content.Context
import android.content.SharedPreferences
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
import com.github.adelina609.stackoverrelations.data.entity.Question
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.presenter.ProfilePresenter
import com.github.adelina609.stackoverrelations.ui.detail.AnswerAdapter
import com.github.adelina609.stackoverrelations.ui.list.OnScrollListener
import com.github.adelina609.stackoverrelations.ui.list.QuestionAdapter
import com.github.adelina609.stackoverrelations.ui.sign_in_up.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @Inject
    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    @ProvidePresenter
    fun getPresenter(): ProfilePresenter = profilePresenter

    private var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun displayQuestions(list: List<Question>) {
        if (rv_questions_fr_profile.adapter == null) {
            rv_questions_fr_profile.adapter = QuestionAdapter(list) {
                profilePresenter.questionClick(it)
            }
        }
        (rv_questions_fr_profile.adapter as QuestionAdapter).submitList(list)    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent
            .builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)

        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        profilePresenter.getQuestions(user?.email)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews() {
        rv_questions_fr_profile.layoutManager = LinearLayoutManager(context)
        rv_questions_fr_profile.addOnScrollListener(OnScrollListener(
            rv_questions_fr_profile.layoutManager as LinearLayoutManager
        ) {
            profilePresenter.getQuestions(user?.email)
        })

        profilePresenter.setUp()

        if(user?.photoUrl != null){
            val uri = user?.photoUrl
            profilePresenter.putPhoto(uri)
            iv_avatar.setImageURI(uri)
        }
    }

    override fun setTexts(username: String?, status : String?){
        tv_username.text = username
        tv_status.text = status
    }

    override fun detachOnScrollListeners() = rv_questions_fr_profile.clearOnScrollListeners()

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_profile, menu)
    }

    override fun setQuestionsValue(questions: Int) {
        tv_questions.text = questions.toString()
        profilePresenter.getAnswers(user?.email)
    }

    override fun setAnswersValue(answers: Int) {
        tv_answers.text = answers.toString()
    }

    override fun displayError() {
        Toast.makeText(context, R.string.server_questions_error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progress.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        progress.visibility = ProgressBar.INVISIBLE
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(SignInActivity.newIntent(context))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sign_out -> signOut()
            R.id.menu_edit -> profilePresenter.goToSettings()
        }
        return true
    }

    companion object {
        fun getInstance(): ProfileFragment = ProfileFragment()

    }
}
