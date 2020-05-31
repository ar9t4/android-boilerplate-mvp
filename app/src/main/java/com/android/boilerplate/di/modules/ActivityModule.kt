package com.android.boilerplate.di.modules

import android.app.Activity
import android.content.Context
import com.android.boilerplate.di.qualifiers.ActivityContext
import com.android.boilerplate.di.scopes.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val context: Context) {

    @PerActivity
    @Provides
    fun provideActivity(): Activity {
        return context as Activity
    }

    @PerActivity
    @ActivityContext
    @Provides
    fun provideActivityContext(): Context {
        return context
    }
}