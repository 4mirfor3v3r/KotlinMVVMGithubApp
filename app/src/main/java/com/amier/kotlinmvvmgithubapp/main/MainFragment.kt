package com.amier.kotlinmvvmgithubapp.main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amier.kotlinmvvmgithubapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var viewbinding:FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("BGT","OPENED")

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
        fun newInstance() = MainFragment().apply {

        }
    }



}
