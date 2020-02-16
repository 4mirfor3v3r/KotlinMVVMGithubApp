package com.amier.kotlinmvvmgithubapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.amier.kotlinmvvmgithubapp.R
import com.amier.kotlinmvvmgithubapp.repo.RepoActivity
import com.amier.kotlinmvvmgithubapp.util.obtainViewModel
import com.amier.kotlinmvvmgithubapp.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {
    lateinit var mActivity:AppCompatActivity
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mActivity = this
        setupFragment()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply{
            openRepo.observe(this@MainActivity, Observer {
                startActivity(Intent(mActivity, RepoActivity::class.java))
            })
        }
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameMain)
        MainFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameMain)
        }
    }
    fun obtainViewModel():MainViewModel = obtainViewModel(MainViewModel::class.java)
}
