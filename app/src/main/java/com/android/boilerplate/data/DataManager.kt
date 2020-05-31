package com.android.boilerplate.data

import com.android.boilerplate.data.database.Database
import com.android.boilerplate.data.network.NetworkService
import com.android.boilerplate.data.preference.Preferences
import javax.inject.Inject

class DataManager @Inject constructor(
    val preferences: Preferences,
    val database: Database,
    val network: NetworkService
)