package com.carlos.events.di.modules

import android.util.Log
import com.carlos.eventos.BuildConfig.BASE_URL
import com.carlos.events.data.api.EventsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkModule {

    fun provide() = loadKoinModules(listOf(networkModule))

    private val networkModule = module {
        single {
            val interceptor = HttpLoggingInterceptor {
                Log.e(OK_HTTP, it)
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }

        single {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        single {
            createService<EventsApi>(get(), get())
        }

    }

    companion object {
        private const val OK_HTTP = "Ok Http"
    }
}

private inline fun <reified T> createService(
    client: OkHttpClient,
    factory: Moshi,
): T {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create(T::class.java)
}