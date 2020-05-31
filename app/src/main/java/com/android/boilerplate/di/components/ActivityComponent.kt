package com.android.boilerplate.di.components

import android.app.Activity
import android.content.Context
import com.android.boilerplate.di.modules.ActivityModule
import com.android.boilerplate.di.qualifiers.ActivityContext
import com.android.boilerplate.di.scopes.PerActivity
import com.android.boilerplate.ui.home.HomeFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun getActivity(): Activity

    @ActivityContext
    fun getActivityContext(): Context

    fun inject(fragment: HomeFragment)
}