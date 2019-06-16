package com.itis.android.firebasesimple.activity

import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.adelina609.stackoverrelations.ui.sign_in_up.SignUpView

class SignUpFragment : MvpAppCompatFragment(), SignUpView {



    companion object {
        fun getInstance(): SignUpFragment = SignUpFragment()
    }

}
