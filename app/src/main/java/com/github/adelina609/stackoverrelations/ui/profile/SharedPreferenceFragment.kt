package com.github.adelina609.stackoverrelations.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.presenter.ProfilePresenter
import com.github.adelina609.stackoverrelations.presenter.SharedPreferencePresenter
import com.google.firebase.auth.FirebaseAuth
import dagger.Provides
import javax.inject.Inject

class SharedPreferenceFragment
    : PreferenceFragmentCompat(),  SharedPreferences.OnSharedPreferenceChangeListener, SharedPreferenceView  {

    private lateinit var listPreference: ListPreference
    private lateinit var username: EditTextPreference
    private lateinit var email: EditTextPreference


    @Inject
    @InjectPresenter
    lateinit var sharedPrefsPresenter: SharedPreferencePresenter

    @ProvidePresenter
    fun getPresenter(): SharedPreferencePresenter = sharedPrefsPresenter


//    @Inject
//    lateinit var prefs: SharedPreferences
//
//    fun getPreferences(): SharedPreferences = prefs

    //var prefs: SharedPreferences? = activity?.getSharedPreferences(NAME, Context.MODE_PRIVATE)


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefs_screen)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "et_username" -> {
                val pref = findPreference(key) as EditTextPreference
                Toast.makeText(activity!!, "String changed to ${pref.text}", Toast.LENGTH_SHORT).show()
                pref.title = "username: ${pref.text}"
                sharedPrefsPresenter.setUsername(pref.text)
            }
            "et_email" -> {
                val email = preferenceManager.findPreference(key) as EditTextPreference
                email.title = email.text
                sharedPrefsPresenter.setEmail(email.text)
            }
            "list_status" -> {
                val status = preferenceManager.findPreference(key) as ListPreference
                val index = status.findIndexOfValue(status.value)
                val entry = status.entries[index]
                status.title = "status: $entry"
                sharedPrefsPresenter.setStatus(entry.toString())
                //status.setSummary(preference.title)
//                prefs?.edit()?.putString(STATUS, entry.toString())?.apply()
            }
//        email.setOnPreferenceChangeListener { preference, newValue ->
//            preference.title = "email: $newValue"
//            FirebaseAuth.getInstance().currentUser?.updateEmail(newValue.toString())
//            prefs?.edit()?.putString(EMAIL, newValue.toString())?.apply()
//            true
//        }
            }
        }



    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent.builder()
            .appComponent(App.getAppComponents())
            .questionModule(QuestionModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        sharedPrefsPresenter.getTitles()
    }

    override fun setTexts(status : String?, username : String?, email : String?){
        println("SEEEEEEEEEEEEEEEEEEEEEEt TEEEEEEEEEEEEEEEEXT AAAAAAAAAAAAAAAAA " + status+ username+ email)
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
        //username.text = prefs?.getString(USERNAME, "defval")
        username.setOnPreferenceChangeListener { preference, newValue ->
            //tv_username.text = newValue.toString()
            preference.title = "username: $newValue"
            sharedPrefsPresenter.setUsername(newValue.toString())
            //prefs?.edit()?.putString(USERNAME, newValue.toString())?.apply()
            true
        }
        email.setOnPreferenceChangeListener { preference, newValue ->
            preference.title = "email: $newValue"
            FirebaseAuth.getInstance().currentUser?.updateEmail(newValue.toString())
            sharedPrefsPresenter.setEmail(newValue.toString())
            //prefs?.edit()?.putString(EMAIL, newValue.toString())?.apply()
            true
        }
        //println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+prefs?.getString(STATUS, "def"))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        fun getInstance(): SharedPreferenceFragment = SharedPreferenceFragment()
    }

}
