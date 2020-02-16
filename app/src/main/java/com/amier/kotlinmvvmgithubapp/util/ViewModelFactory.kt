package com.amier.kotlinmvvmgithubapp.util

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amier.kotlinmvvmgithubapp.data.source.MainDataRepository
import com.amier.kotlinmvvmgithubapp.main.MainViewModel
import com.amier.kotlinmvvmgithubapp.repo.RepoViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val application: Application,
    private val mainDataRepository: MainDataRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>)= with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) -> MainViewModel(application, mainDataRepository)
            isAssignableFrom(RepoViewModel::class.java) -> RepoViewModel(
                application,
                mainDataRepository
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }as T

    companion object{
        @Volatile private var INSTANCE :ViewModelFactory? = null
        fun getInstance(application: Application) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(application,Injection.providedMainDataRepository(application.applicationContext))
                .also { INSTANCE = it }
        }
        @VisibleForTesting fun destroyInstance(){
            INSTANCE = null
        }
    }

}