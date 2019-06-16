package com.github.adelina609.stackoverrelations.ui.profile

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Question
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    override fun displayQuestions(list: List<Question>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent
            .builder()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_profile, menu)
    }

    override fun setQuestionsValue(questions: Int) {
        questions_count.text = questions.toString()
    }

    override fun setAnswersValue(answers: Int) {
        answers_count.text = answers.toString()
    }
    override fun displayError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun detachOnScrollListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
