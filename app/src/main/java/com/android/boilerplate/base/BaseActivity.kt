package com.android.boilerplate.base

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.boilerplate.Application
import com.android.boilerplate.di.components.ActivityComponent
import com.android.boilerplate.di.components.DaggerActivityComponent
import com.android.boilerplate.di.modules.ActivityModule
import com.android.boilerplate.utils.DialogUtils

abstract class BaseActivity : AppCompatActivity(), MvpView {

    private var dialog: Dialog? = null
    private var availableNetwork: Network? = null
    private var originalSoftInputMode: Int? = null
    private lateinit var inputManager: InputMethodManager
    private lateinit var activityComponent: ActivityComponent
    private lateinit var connectivityManager: ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            availableNetwork = network
            runOnUiThread { hasConnectivity(true) }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            if (network == availableNetwork) {
                runOnUiThread { hasConnectivity(false) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = DialogUtils.createProgressDialog(this)
        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        activityComponent = DaggerActivityComponent
            .builder()
            .applicationComponent((application as Application).applicationComponent)
            .activityModule(
                ActivityModule(
                    this
                )
            )
            .build()
        registerNetworkCallback()
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }

    override fun hideKeyboard() {
        this.currentFocus?.let {
            inputManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun showKeyboard(editText: EditText) {
        editText.post {
            editText.requestFocus()
            inputManager.showSoftInput(editText.rootView, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    override fun setSoftInputMode(mode: Int) {
        originalSoftInputMode = window.attributes.softInputMode
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun resetSoftInputMode() {
        originalSoftInputMode?.let {
            window.setSoftInputMode(it)
        }
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun sessionExpire() {
        Toast.makeText(this, "session has been expired", Toast.LENGTH_SHORT).show()
    }

    override fun noConnectivity() {
        Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show()
    }

    override fun loaderVisibility(visibility: Boolean) {
        if (visibility)
            dialog?.show()
        else
            dialog?.dismiss()
    }

    fun getActivityComponent(): ActivityComponent {
        return activityComponent
    }

    abstract fun hasConnectivity(connectivity: Boolean)

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}