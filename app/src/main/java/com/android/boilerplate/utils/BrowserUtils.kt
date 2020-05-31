package com.android.boilerplate.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.android.boilerplate.R

object BrowserUtils {

    fun openInAppBrowser(context: Context, url: String) {
        val tabsIntentBuilder = CustomTabsIntent.Builder()
        tabsIntentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        val tabsIntent = tabsIntentBuilder.build()
        tabsIntent.launchUrl(context, Uri.parse(url))
    }
}