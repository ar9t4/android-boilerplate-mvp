package com.android.boilerplate.base

import android.widget.EditText

interface MvpView {
    fun hideKeyboard()
    fun showKeyboard(editText: EditText)
    fun setSoftInputMode(mode: Int)
    fun resetSoftInputMode()
    fun showError(error: String)
    fun showMessage(message: String)
    fun sessionExpire()
    fun noConnectivity()
    fun loaderVisibility(visibility: Boolean)
}