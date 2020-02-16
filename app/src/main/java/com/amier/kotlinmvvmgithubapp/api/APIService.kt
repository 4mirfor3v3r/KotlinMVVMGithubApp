package com.amier.kotlinmvvmgithubapp.api

import com.amier.kotlinmvvmgithubapp.api.dao.MainDataDao
import com.amier.kotlinmvvmgithubapp.api.dao.RepoDataDao
import com.amier.kotlinmvvmgithubapp.util.Constant
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//import android.database.Observable
import retrofit2.http.GET
import retrofit2.http.Path
//import java.util.*

interface APIService {
    @GET("users/{username}")
    fun getMainData(@Path("username") username:String): Observable<MainDataDao>

    @GET("https://api.github.com/users/{username}/repos")
    fun getReposData(
        @Path("username") username: String
    ): Observable<List<RepoDataDao>>

    companion object Factory {

        fun create():APIService{

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}