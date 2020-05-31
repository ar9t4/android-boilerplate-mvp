package com.android.boilerplate.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.base.BaseFragment
import com.android.boilerplate.databinding.FragmentDeepLinkBinding

class DeepLinkFragment : BaseFragment() {

    private lateinit var binding: FragmentDeepLinkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deep_link, container, false)
        binding.listener = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val source = arguments?.getString("source") ?: "App"
        binding.source.text = source
    }

    fun sendNotification() {
        if (binding.deepLinkArgs.text.toString().trim().isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please provide some arguments!",
                Toast.LENGTH_LONG
            ).show()
        } else {
            hideKeyboard()
            val deepLinkArgs = view?.findViewById<AppCompatEditText>(R.id.deep_link_args)
            val args = Bundle()
            args.putString("source", deepLinkArgs?.text.toString())

            val deepLink = findNavController().createDeepLink()
                .setDestination(R.id.deep_link_dest)
                .setArguments(args)
                .createPendingIntent()

            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        "deepLink",
                        "Deep Links",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }
            val builder = NotificationCompat.Builder(requireContext(), "deepLink")
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Deep link to Android")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(deepLink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }
}