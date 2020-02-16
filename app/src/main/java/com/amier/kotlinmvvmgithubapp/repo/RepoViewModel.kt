package com.amier.kotlinmvvmgithubapp.repo

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.amier.kotlinmvvmgithubapp.data.RepoData
import com.amier.kotlinmvvmgithubapp.data.source.MainDataRepository
import com.amier.kotlinmvvmgithubapp.data.source.MainDataSource
import com.amier.kotlinmvvmgithubapp.util.SingleLiveEvent

class RepoViewModel(application: Application, private val mainDataRepository: MainDataRepository) :
    AndroidViewModel(application) {
    val repoList: ObservableList<RepoData> = ObservableArrayList()
    internal val openRepo = SingleLiveEvent<String>()

    fun start() {
        getRepos()
    }

    private fun getRepos() {
        mainDataRepository.getRepoData(object : MainDataSource.GetRepoCallback {
            override fun onDataLoaded(repoData: MutableList<RepoData?>) {
                with(repoList) {
                    clear()
                    addAll(repoData)
                }
            }

            override fun onNotAvailable() {
                Toast.makeText(getApplication(), "No data Found", Toast.LENGTH_SHORT).show()
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(), "Error : $msg", Toast.LENGTH_SHORT).show()
            }
        })
    }
}