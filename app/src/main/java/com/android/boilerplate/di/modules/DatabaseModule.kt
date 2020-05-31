package com.android.boilerplate.di.modules

import android.content.Context
import androidx.room.Room
import com.android.boilerplate.R
import com.android.boilerplate.data.database.Database
import com.android.boilerplate.di.qualifiers.ApplicationContext
import com.android.boilerplate.di.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @PerApplication
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            context.getString(R.string.app_name)
        ).build()
    }
}