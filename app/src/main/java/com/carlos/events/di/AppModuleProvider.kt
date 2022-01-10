package com.carlos.events.di

import com.carlos.events.di.modules.NetworkModule
import com.carlos.events.di.modules.RepositoryModule
import com.carlos.events.di.modules.UseCaseGetModule
import com.carlos.events.di.modules.ViewModelsModule

class AppModuleProvider( private val params: AppFeaturesParams) {

    fun provide() = listOf(
        NetworkModule().provide(),
        RepositoryModule().provide(),
        UseCaseGetModule().provide(),
        ViewModelsModule().provide(),
    )
}