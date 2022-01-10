package com.carlos.events

import android.content.Intent
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

private const val serverPort = 8080

fun onActivity(func: MainActivityRobot.() -> Unit) = MainActivityRobot().apply { func() }

class MainActivityRobot : MainActivityAssertionRobot() {

    private val server = MockWebServer()

    fun setUp() {
        server.start(serverPort)

        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {

                    "path/endpoint" -> {
                        successResponse
                    }
                    else -> {
                        errorResponse
                    }
                }
            }
        }
    }

    fun launch() {
        activityRule.launchActivity(Intent())
    }

    companion object {
        private val successResponse by lazy {
            MockResponse()
                .setResponseCode(200)
                .setBody("JSON RESPONSE")
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(503) }
    }
}