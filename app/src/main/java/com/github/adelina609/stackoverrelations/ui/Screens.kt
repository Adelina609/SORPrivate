package com.github.adelina609.stackoverrelations.ui

import androidx.fragment.app.Fragment
import com.github.adelina609.stackoverrelations.ui.detail.DetailsFragment
import com.github.adelina609.stackoverrelations.ui.list.ListFragment
import com.github.adelina609.stackoverrelations.ui.new_answer.NewAnswerFragment
import com.github.adelina609.stackoverrelations.ui.new_question.NewQuestionFragment
import com.github.adelina609.stackoverrelations.ui.profile.ProfileFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = ListFragment.getInstance()
    }

    class DetailScreen(private val id: Long) : SupportAppScreen() {
        override fun getFragment(): Fragment = DetailsFragment.getInstance(id)
    }

//    class LinksScreen(private val position: Int) : SupportAppScreen() {
//        override fun getFragment(): Fragment = LinksFragment.getInstance(position)
//    }

    class NewQuestionScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = NewQuestionFragment.getInstance()
    }

    class NewAnswerScreen(private val email : String, private val qId : Long) : SupportAppScreen() {
        override fun getFragment(): Fragment = NewAnswerFragment.getInstance(email, qId)
    }

    class ProfileScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment = ProfileFragment.getInstance()
    }
}
