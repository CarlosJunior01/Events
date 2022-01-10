package com.carlos.events.util

sealed class StateResponse {
    object StateLoading: StateResponse()
    object StateSuccess: StateResponse()
    object StateError: StateResponse()
}
