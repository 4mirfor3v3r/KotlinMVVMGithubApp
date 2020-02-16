package com.amier.kotlinmvvmgithubapp.util

import android.content.Context
import androidx.preference.PreferenceManager
import com.amier.kotlinmvvmgithubapp.data.source.MainDataRepository
import com.amier.kotlinmvvmgithubapp.data.source.local.MainDataLocalSource
import com.amier.kotlinmvvmgithubapp.data.source.remote.MainDataRemoteSource

object Injection {
    fun providedMainDataRepository(context: Context) = MainDataRepository.getInstance(MainDataRemoteSource,
        MainDataLocalSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context))!!)
}