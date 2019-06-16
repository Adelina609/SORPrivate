package com.github.adelina609.stackoverrelations.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.github.adelina609.stackoverrelations.R

class SharedPreference : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs_screen, rootKey);
    }

    private lateinit var listPreference: ListPreference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listPreference = preferenceManager.findPreference("list_status") as ListPreference
        listPreference.setOnPreferenceChangeListener { preference, newValue ->
            if (preference is ListPreference) {
                val index = preference.findIndexOfValue(newValue.toString())
                val entry = preference.entries[index]
                preference.title = "status ($entry)"
            }
            true
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
