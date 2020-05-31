package com.android.boilerplate.di.modules

import android.content.Context
import com.android.boilerplate.Application
import com.android.boilerplate.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val context: Context) {

    @Provides
    fun provideApplication(): Application {
        return context as Application
    }

    @ApplicationContext
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }
}