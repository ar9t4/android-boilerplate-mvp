package com.android.boilerplate.base

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.data.models.NetworkError
import com.android.boilerplate.utils.NetworkUtils
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class BasePresenter<V : MvpView> :
    MvpPresenter<V> {

    private var mvpView: V? = null
    private lateinit var gson: Gson
    private lateinit var context: Context
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
        gson = Gson()
        compositeDisposable = CompositeDisposable()
        context = if (getMvpView() is BaseFragment)
            (getMvpView() as BaseFragment).getBaseActivity()!!
        else
            getMvpView() as BaseActivity
    }

    override fun onDetach() {
        this.mvpView = null
        compositeDisposable.dispose()
    }

    fun getMvpView(): V? {
        return mvpView
    }

    fun getGson(): Gson {
        return gson
    }

    fun getContext(): Context {
        return context
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    fun isConnectedToInternet(): Boolean {
        return NetworkUtils.isConnectedToInternet(context)
    }

    fun parseThrowable(throwable: Throwable): Any {
        getMvpView() ?: return true
        getMvpView()?.loaderVisibility(false)
        when (throwable) {
            is TimeoutException -> {
                getMvpView()?.showError(context.getString(R.string.general_error))
                return true
            }
            is SocketTimeoutException -> {
                getMvpView()?.showError(context.getString(R.string.general_error))
                return true
            }
            is UnknownHostException -> {
                getMvpView()?.noConnectivity()
                return true
            }
            is HttpException -> {
                if (throwable.code() == 401) {
                    getMvpView()?.sessionExpire()
                    return true
                } else {
                    return try {
                        val networkError = gson.fromJson(
                            throwable.response()?.errorBody()?.charStream(),
                            NetworkError::class.java
                        )
                        // status will be 0 (it is the default value of Int in NetworkError class) when there will be no code sent from server
                        if (networkError.status == 0) {
                            getMvpView()?.showError(context.getString(R.string.facing_difficulties))
                            true
                        } else {
                            networkError
                        }
                    } catch (exception: Exception) {
                        getMvpView()?.showError(throwable.toString())
                        true
                    }
                }
            }
            else -> {
                getMvpView()?.showError(throwable.toString())
                return true
            }
        }
    }
}