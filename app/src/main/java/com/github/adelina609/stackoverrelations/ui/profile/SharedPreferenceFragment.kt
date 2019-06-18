package com.github.adelina609.stackoverrelations.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.presenter.SharedPreferencePresenter
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SharedPreferenceFragment
    : PreferenceFragmentCompat(), SharedPreferenceView {

    private lateinit var listPreference: ListPreference
    private lateinit var username: EditTextPreference
    private lateinit var email: EditTextPreference

    @Inject
    @InjectPresenter
    lateinit var sharedPrefsPresenter: SharedPreferencePresenter

    @ProvidePresenter
    fun getPresenter(): SharedPreferencePresenter = sharedPrefsPresenter

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefs_screen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        sharedPrefsPresenter.getTitles()
    }

    override fun setTexts(status: String?, username: String?, email: String?) {
        this.listPreference.title = status
        this.username.title = username
        this.email.title = email

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listPreference = preferenceManager.findPreference("list_status") as ListPreference
        username = preferenceManager.findPreference("et_username") as EditTextPreference
        email = preferenceManager.findPreference("et_email") as EditTextPreference

        listPreference.setOnPreferenceChangeListener { preference, newValue ->
            if (preference is ListPreference) {
                val index = preference.findIndexOfValue(newValue.toString())
                val entry = preference.entries[index]
                preference.title = "status: $entry"
                preference.setSummary(preference.title)
                sharedPrefsPresenter.setStatus(entry.toString())
            }
            true
        }
        username.setOnPreferenceChangeListener { preference, newValue ->
            preference.title = "username: $newValue"
            sharedPrefsPresenter.setUsername(newValue.toString())
            true
        }
        email.setOnPreferenceChangeListener { preference, newValue ->
            preference.title = "email: $newValue"
            FirebaseAuth.getInstance().currentUser?.updateEmail(newValue.toString())
            sharedPrefsPresenter.setEmail(newValue.toString())
            true
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        fun getInstance(): SharedPreferenceFragment = SharedPreferenceFragment()
    }
}
