package com.amier.kotlinmvvmgithubapp.repo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amier.kotlinmvvmgithubapp.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {
    private lateinit var viewBinding: FragmentRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRepoBinding.inflate(inflater, container, false).apply {
            vm = (activity as RepoActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRepo()
    }

    private fun setupRepo() {
        val viewmodel = viewBinding.vm
        if (viewmodel != null) {
            repoAdapter = RepoAdapter(viewmodel.repoList, viewmodel)
            viewBinding.rvRepo.adapter = repoAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()
    }

    companion object {
        fun newInstace() = RepoFragment().apply {

        }
    }
}
