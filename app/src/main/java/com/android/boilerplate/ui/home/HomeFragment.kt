package com.android.boilerplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.boilerplate.R
import com.android.boilerplate.base.BaseFragment
import com.android.boilerplate.data.database.entities.Repo
import com.android.boilerplate.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMvpView {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var presenter: HomePresenter<HomeMvpView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.listener = this

        getActivityComponent()?.inject(this)
        presenter.onAttach(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }

    override fun showRepos(repos: List<Repo>) {

    }

    fun onNavigateToDestinationClicked() {
        findNavController().navigate(R.id.flow_step_one_dest)
    }

    fun onNavigateWithActionClicked() {
        findNavController().navigate(R.id.next_action)
    }

    fun onFetchReposClicked() {
        presenter.getRepos("octocat")
    }
}