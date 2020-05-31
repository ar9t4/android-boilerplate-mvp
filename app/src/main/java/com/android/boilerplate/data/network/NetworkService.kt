package com.android.boilerplate.data.network

import com.android.boilerplate.data.database.entities.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("users/{user}/repos")
    fun getRepositories(@Path("user") user: String): Single<List<Repo>>
}