package com.github.kornilovmikhail.mvpandroidproject.ui

import androidx.fragment.app.Fragment
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.new_answer.NewAnswerFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.new_question.NewQuestionFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = ListFragment.getInstance()
    }

    class DetailScreen(private val position: Long) : SupportAppScreen() {
        override fun getFragment(): Fragment = DetailsFragment.getInstance(position)
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
}
