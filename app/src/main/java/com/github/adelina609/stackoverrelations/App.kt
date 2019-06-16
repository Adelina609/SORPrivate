package com.github.adelina609.stackoverrelations

import android.app.Application
import com.github.adelina609.stackoverrelations.di.app.component.AppComponent
import com.github.adelina609.stackoverrelations.di.app.component.DaggerAppComponent

import com.github.adelina609.stackoverrelations.di.app.module.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .dataDBModule(DataDBModule())
            .dataNetModule(DataNetModule())
            .sharedPreferencesModule(SharedPreferencesModule())
            .navigationModule(NavigationModule())
            .build()
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun getAppComponents(): AppComponent? = appComponent
    }
}
