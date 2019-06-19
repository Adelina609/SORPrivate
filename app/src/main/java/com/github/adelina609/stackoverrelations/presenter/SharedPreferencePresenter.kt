package com.github.adelina609.stackoverrelations.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.adelina609.stackoverrelations.ui.profile.SharedPreferenceView
import com.github.adelina609.stackoverrelations.utils.ConstantValues
import ru.terrakok.cicerone.Router

@InjectViewState
class SharedPreferencePresenter(private val router : Router,
                                private val sharedPreferences: SharedPreferences)
    : MvpPresenter<SharedPreferenceView>() {

    fun getTitles(){
        viewState.setTexts(sharedPreferences.getString(ConstantValues.STATUS, ConstantValues.STATUS),
            sharedPreferences.getString(ConstantValues.USERNAME, ConstantValues.USERNAME),
            sharedPreferences.getString(ConstantValues.EMAIL, ConstantValues.EMAIL))
    }

    fun setUsername(username : String){
        sharedPreferences.edit()?.putString(ConstantValues.USERNAME, username)?.apply()
    }

    fun setEmail(email : String){
        sharedPreferences.edit()?.putString(ConstantValues.EMAIL, email)?.apply()
    }

    fun setStatus(status : String){
        sharedPreferences.edit()?.putString(ConstantValues.STATUS, status)?.apply()
    }
}