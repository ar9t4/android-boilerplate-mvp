package com.android.boilerplate.di.components

import android.content.Context
import com.android.boilerplate.Application
import com.android.boilerplate.data.DataManager
import com.android.boilerplate.di.modules.ApplicationModule
import com.android.boilerplate.di.modules.DatabaseModule
import com.android.boilerplate.di.modules.NetworkModule
import com.android.boilerplate.di.qualifiers.ApplicationContext
import com.android.boilerplate.di.scopes.PerApplication
import dagger.Component

@PerApplication
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun getApplication(): Application

    @ApplicationContext
    fun getApplicationContext(): Context

    fun getDataManager(): DataManager
}