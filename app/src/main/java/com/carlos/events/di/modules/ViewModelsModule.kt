package com.carlos.events.di.modules

import com.carlos.eventos.presenter.viewmodels.EventsViewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class ViewModelsModule {

    fun provide() = loadKoinModules( listOf(
        viewModelModule
    ))

    private val viewModelModule = module {
            factory { EventsViewModel(get(), get()) }
        }
    }