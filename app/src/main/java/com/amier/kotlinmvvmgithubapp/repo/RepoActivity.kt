package com.amier.kotlinmvvmgithubapp.repo

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.amier.kotlinmvvmgithubapp.R
import com.amier.kotlinmvvmgithubapp.util.obtainViewModel
import com.amier.kotlinmvvmgithubapp.util.replaceFragmentInActivity

class RepoActivity : AppCompatActivity() {
    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        mActivity = this
        setupViewModel()
        setupFragment()
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openRepo.observe(mActivity, Observer {
                onRepoClicked(it!!)
            })
        }
    }

    private fun onRepoClicked(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(mActivity, R.color.colorPrimary))
        customTabsIntent.launchUrl(mActivity, Uri.parse(url))
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameRepo)
        RepoFragment.newInstace().let {
            replaceFragmentInActivity(it, R.id.frameRepo)
        }
    }

    fun obtainViewModel(): RepoViewModel = obtainViewModel(RepoViewModel::class.java)
}
