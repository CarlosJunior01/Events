package com.carlos.events

import android.app.Application
import com.carlos.events.di.modules.dataModules
import com.carlos.events.di.modules.domainModules
import com.carlos.events.di.modules.presentationModules
import com.carlos.events.di.modules.networkModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(domainModules)
            modules(dataModules)
            modules(presentationModules)
            modules(networkModules)
        }
    }
}