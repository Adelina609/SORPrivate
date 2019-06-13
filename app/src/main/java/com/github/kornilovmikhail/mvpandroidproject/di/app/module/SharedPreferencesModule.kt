package com.github.kornilovmikhail.mvpandroidproject.di.app.module

import android.content.Context
import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {
    companion object {
        const val NAME_SHAREDPREFS: String = "PaginationPreferencesRepo"
    }

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(NAME_SHAREDPREFS, Context.MODE_PRIVATE)
}
