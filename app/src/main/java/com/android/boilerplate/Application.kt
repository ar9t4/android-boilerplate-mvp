package com.android.boilerplate

import android.app.Application
import com.android.boilerplate.di.components.ApplicationComponent
import com.android.boilerplate.di.components.DaggerApplicationComponent
import com.android.boilerplate.di.modules.ApplicationModule
import com.facebook.stetho.Stetho

class Application : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}