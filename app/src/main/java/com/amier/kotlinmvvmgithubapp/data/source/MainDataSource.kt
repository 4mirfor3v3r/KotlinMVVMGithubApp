package com.amier.kotlinmvvmgithubapp.data.source

import com.amier.kotlinmvvmgithubapp.data.MainData
import com.amier.kotlinmvvmgithubapp.data.RepoData

interface MainDataSource {
    fun getMainData(callback:GetMainDataCallback)
    fun getRepoData(callback:GetRepoCallback)

    interface GetRepoCallback {
        fun onDataLoaded(repoData: MutableList<RepoData?>)
        fun onNotAvailable()
        fun onError(msg:String?)
    }

    interface GetMainDataCallback {
        fun onDataLoaded(mainData:MainData?)
        fun onNotAvailable()
        fun onError(msg:String?)
    }
}