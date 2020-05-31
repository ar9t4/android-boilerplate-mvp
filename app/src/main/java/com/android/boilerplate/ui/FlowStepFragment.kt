package com.android.boilerplate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.boilerplate.R
import com.android.boilerplate.base.BaseFragment

class FlowStepFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val args: FlowStepFragmentArgs by navArgs()
        val flowStepNumber = args.flowStepNumber
        return if (flowStepNumber == 1)
            inflater.inflate(R.layout.fragment_flow_step_one, container, false)
        else
            inflater.inflate(R.layout.fragment_flow_step_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<AppCompatButton>(R.id.navigate_next_step)?.setOnClickListener {
            findNavController().navigate(R.id.next_action)
        }
    }
}