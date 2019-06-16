package com.github.adelina609.stackoverrelations.ui.profile

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

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
}