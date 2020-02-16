package com.amier.kotlinmvvmgithubapp.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.amier.kotlinmvvmgithubapp.api.APIService
import com.amier.kotlinmvvmgithubapp.api.dao.RepoDataDao
import com.amier.kotlinmvvmgithubapp.data.MainData
import com.amier.kotlinmvvmgithubapp.data.RepoData
import com.amier.kotlinmvvmgithubapp.data.source.MainDataSource
import com.amier.kotlinmvvmgithubapp.util.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
object MainDataRemoteSource:MainDataSource {
    private val apiService = APIService.create()

    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        apiService.getMainData(Constant.username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    if (it.name != ""){
                        val mainData = MainData(
                            it.name,
                            it.location,
                            it.avatar_url,
                            "${it.followers}\nFollowers",
                            "${it.following}\nFollowing",
                            "${it.public_repos}\nRepos"
                        )
                        callback.onDataLoaded(mainData)
                    }else{
                        callback.onNotAvailable()
                    }
                }
            },{
                callback.onError(it.message)
            })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoCallback) {
        apiService.getReposData(Constant.username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    if (it.isNotEmpty()) {
                        Log.i("xx", " ${it.size}")
                        val listRepo: MutableList<RepoData?> = mutableListOf()
                        for (item: RepoDataDao in it) {
                            val repoData = RepoData(
                                item.name,
                                item.language,
                                item.description,
                                item.html_url
                            )

                            listRepo.add(repoData)
                        }
                        callback.onDataLoaded(listRepo)
                    }else{
                        callback.onNotAvailable()
                    }
                }
            },{
                callback.onError(it.message)
            })
    }
}