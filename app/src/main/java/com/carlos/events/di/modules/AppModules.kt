package com.carlos.events.di.modules

import com.carlos.events.data.api.EventsApi
import com.carlos.events.data.repository.EventsRepository
import com.carlos.events.data.repository.EventsRepositoryImpl
import com.carlos.events.data.retrofit.HttpClient
import com.carlos.events.data.retrofit.RetrofitClient
import com.carlos.events.domain.usecase.CheckInUseCase
import com.carlos.events.domain.usecase.CheckInUseCaseImpl
import com.carlos.events.domain.usecase.GetEventsUseCase
import com.carlos.events.domain.usecase.GetEventsUseCaseImpl
import com.carlos.eventos.presenter.viewmodels.EventsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModules = module {
    single<GetEventsUseCase> { GetEventsUseCaseImpl(get()) }
    single<CheckInUseCase> { CheckInUseCaseImpl(get()) }
}

val presentationModules = module {
    viewModel { EventsViewModel(get(), get()) }
}

val dataModules = module {
    factory<EventsRepository> { EventsRepositoryImpl(get()) }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(EventsApi::class.java) }
}