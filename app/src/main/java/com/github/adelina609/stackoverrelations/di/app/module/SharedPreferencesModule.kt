package com.github.adelina609.stackoverrelations.di.app.module

import android.content.Context
import android.content.SharedPreferences
import com.github.adelina609.stackoverrelations.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {
    companion object {
        const val NAME_SHAREDPREFS: String = "my_prefs"
    }

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(NAME_SHAREDPREFS, Context.MODE_PRIVATE)
}
