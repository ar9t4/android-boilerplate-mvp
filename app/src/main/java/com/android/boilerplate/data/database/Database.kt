package com.android.boilerplate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.boilerplate.data.database.daos.RepoDao
import com.android.boilerplate.data.database.entities.Repo

@Database(entities = [Repo::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}