package com.carlos.events.di.modules

import com.carlos.events.domain.usecase.CheckInUseCase
import com.carlos.events.domain.usecase.CheckInUseCaseImpl
import com.carlos.events.domain.usecase.GetEventsUseCase
import com.carlos.events.domain.usecase.GetEventsUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class UseCaseGetModule {

    fun provide() = loadKoinModules(
        listOf(
            getEventsUseCasesModuleKoin,
            chekInUseCasesModuleKoin
        )
    )

    private val getEventsUseCasesModuleKoin = module {
        single<GetEventsUseCase> { GetEventsUseCaseImpl(get()) }
    }

    private val chekInUseCasesModuleKoin = module {
        single<CheckInUseCase> { CheckInUseCaseImpl(get()) }
    }
}

//}


