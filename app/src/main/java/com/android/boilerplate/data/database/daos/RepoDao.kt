package com.android.boilerplate.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.boilerplate.data.database.entities.Repo

@Dao
interface RepoDao {
    @Query("Select * from repo")
    fun getAll(): List<Repo>

    @Insert
    fun insertAll(vararg repos: Repo)
}