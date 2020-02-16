package com.amier.kotlinmvvmgithubapp.data.source

import com.amier.kotlinmvvmgithubapp.data.MainData
import com.amier.kotlinmvvmgithubapp.data.RepoData
import com.amier.kotlinmvvmgithubapp.data.source.local.MainDataLocalSource

class MainDataRepository(val remoteDataSource:MainDataSource, val localDataSource:MainDataSource):MainDataSource {
    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        remoteDataSource.getMainData(object :MainDataSource.GetMainDataCallback{
            override fun onDataLoaded(mainData: MainData?) {
                callback.onDataLoaded(mainData)
            }
            override fun onNotAvailable() {
                callback.onNotAvailable()
            }
            override fun onError(msg: String?) {
                callback.onError(msg)
            }
        })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoCallback) {
        remoteDataSource.getRepoData(object :MainDataSource.GetRepoCallback{
            override fun onDataLoaded(repoData: MutableList<RepoData?>) {
                callback.onDataLoaded(repoData)
            }
            override fun onNotAvailable() {
                callback.onNotAvailable()
            }
            override fun onError(msg: String?) {
                callback.onError(msg)
            }
        })
    }

    companion object{
        private var INSTANCE:MainDataRepository? = null

        @JvmStatic
        fun getInstance(mainDataRemoteSource: MainDataSource, newsLocalDataSource: MainDataLocalSource) =
            synchronized(MainDataRepository::class.java) {
                INSTANCE ?: MainDataRepository(mainDataRemoteSource, mainDataRemoteSource).also {
                    INSTANCE = it
                }
            }

        @JvmStatic
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}