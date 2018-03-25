@file:Suppress("DEPRECATION")

package com.mynotes.core

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.mynotes.core.di.AppComponent
import com.mynotes.core.di.AppModule
import com.mynotes.core.di.DaggerAppComponent
import com.mynotes.core.di.NetworkModule

/**
 * Created by ark21 on 25-03-2018.
 */
class App : MultiDexApplication() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    fun appComponent(): AppComponent = appComponent;
}