package com.android.boilerplate.base

import android.content.Context
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.android.boilerplate.di.components.ActivityComponent

abstract class BaseFragment : Fragment(),
    MvpView {

    private var activity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity)
            activity = context
    }

    override fun hideKeyboard() {
        activity?.hideKeyboard()
    }

    override fun showKeyboard(editText: EditText) {
        activity?.showKeyboard(editText)
    }

    override fun showError(error: String) {
        activity?.showError(error)
    }

    override fun showMessage(message: String) {
        activity?.showMessage(message)
    }

    override fun sessionExpire() {
        activity?.sessionExpire()
    }

    override fun noConnectivity() {
        activity?.noConnectivity()
    }

    override fun loaderVisibility(visibility: Boolean) {
        activity?.loaderVisibility(visibility)
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun getBaseActivity(): BaseActivity? {
        return activity
    }

    fun getActivityComponent(): ActivityComponent? {
        return activity?.getActivityComponent()
    }
}