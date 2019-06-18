package com.github.adelina609.stackoverrelations.ui.profile

import com.arellomobile.mvp.MvpView

interface SharedPreferenceView : MvpView {
    fun setTexts(status : String?, username : String?, email : String?)
}