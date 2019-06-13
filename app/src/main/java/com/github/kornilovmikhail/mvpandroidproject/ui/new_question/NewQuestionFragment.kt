package com.github.kornilovmikhail.mvpandroidproject.ui.new_question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.kornilovmikhail.mvpandroidproject.R

class NewQuestionFragment : MvpAppCompatFragment(), NewQuestionView {
    override fun sendQuestion() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_new_question, container, false)


}