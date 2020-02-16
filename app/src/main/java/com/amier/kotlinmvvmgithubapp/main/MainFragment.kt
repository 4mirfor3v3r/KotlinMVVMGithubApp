package com.amier.kotlinmvvmgithubapp.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amier.kotlinmvvmgithubapp.R
import com.amier.kotlinmvvmgithubapp.databinding.FragmentMainBinding
import com.amier.kotlinmvvmgithubapp.util.obtainViewModel

class MainFragment : Fragment() {
    lateinit var viewbinding:FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_main, container, false)

        viewbinding = FragmentMainBinding.inflate(inflater,container,false).apply {
            vm = (activity as MainActivity).obtainViewModel()

            action = object :MainActionListener{
                override fun onClickRepos(){
                    vm?.openRepo()
                }
            }
        }
        return viewbinding.root
    }

    override fun onResume() {
        super.onResume()
        viewbinding.vm?.start()
    }
    companion object{
        fun newInstance() = MainFragment.apply {

        }
    }



}
