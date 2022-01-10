package com.carlos.events.di.modules

import com.carlos.events.data.repository.EventsRepository
import com.carlos.events.data.repository.EventsRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class RepositoryModule {

    fun provide() = loadKoinModules(listOf(repositoryModule))

    private val repositoryModule = module {
        single<EventsRepository> { EventsRepositoryImpl(get()) }
    }
}