package com.android.boilerplate.ui.home

import com.android.boilerplate.base.BasePresenter
import com.android.boilerplate.data.DataManager
import com.android.boilerplate.data.database.entities.Repo
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter<V : HomeMvpView> @Inject constructor(private val dataManager: DataManager) :
    BasePresenter<V>(), HomeMvpPresenter {

    override fun getRepos(user: String) {
        if (!isConnectedToInternet()) {
            getMvpView()?.noConnectivity()
            return
        }
        dataManager.network.getRepositories(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<Repo>> {
                override fun onSubscribe(d: Disposable) {
                    getMvpView()?.loaderVisibility(true)
                    getCompositeDisposable().add(d)
                }

                override fun onSuccess(t: List<Repo>) {
                    getMvpView()?.loaderVisibility(false)
                    getMvpView()?.showRepos(t)
                }

                override fun onError(e: Throwable) {
                    parseThrowable(e)
                }
            })
    }
}