package com.android.boilerplate.ui.home

import com.android.boilerplate.base.MvpView
import com.android.boilerplate.data.database.entities.Repo

interface HomeMvpView: MvpView {
    fun showRepos(repos: List<Repo>)
}