package com.github.adelina609.stackoverrelations.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.data.repository.PreferencesRepo
import com.github.adelina609.stackoverrelations.ui.profile.SharedPreferenceView
import ru.terrakok.cicerone.Router
import kotlin.math.E

@InjectViewState
class SharedPreferencePresenter(private val router : Router,
                                private val sharedPreferences: SharedPreferences)
    : MvpPresenter<SharedPreferenceView>() {

    private val STATUS = "status"
    private val USERNAME = "username"
    private val EMAIL = "email"

    fun getTitles(){
        viewState.setTexts(sharedPreferences.getString(STATUS, STATUS),
            sharedPreferences.getString(USERNAME, USERNAME), sharedPreferences.getString(EMAIL, EMAIL))
    }

    fun setUsername(username : String){
        sharedPreferences.edit()?.putString(USERNAME, username)?.apply()
    }

    fun setEmail(email : String){
        sharedPreferences.edit()?.putString(EMAIL, email)?.apply()
    }

    fun setStatus(status : String){
        sharedPreferences.edit()?.putString(STATUS, status)?.apply()
    }
}