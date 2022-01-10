package com.carlos.events.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.events.util.StateResponse
import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.domain.model.EventsViewObject
import com.carlos.events.domain.usecase.CheckInUseCase
import com.carlos.events.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventsViewModel (
    private val getEventsUseCase: GetEventsUseCase,
    private val checkInUseCase: CheckInUseCase
) : ViewModel() {

    private val _screenState = Channel<StateResponse>()
    val screenState: Flow<StateResponse> = _screenState.receiveAsFlow()

    private val _viewEvents = MutableLiveData<List<EventsViewObject>>()
    val viewEvents = _viewEvents as LiveData<List<EventsViewObject>>

    fun getEvents() {
        viewModelScope.launch {
            getEventsUseCase.getEvents()
                .onStart {
                    _screenState.send(StateResponse.StateLoading)
                }

                .catch { error ->
                    Log.i(APP_TAG, "errorMessage: ${error.message}" )
                    _screenState.send(StateResponse.StateError)
                }

                .collect { val eventsViewObject = it.map { events -> EventsViewObject(events) }

                    _viewEvents.postValue(eventsViewObject)
                    _screenState.send(StateResponse.StateSuccess)
                }
        }
    }

    fun eventChekIn(checkInResponse: CheckInResponse) {
        viewModelScope.launch {
            checkInUseCase.checkIn(checkInResponse)
                .onStart { _screenState.send(StateResponse.StateLoading) }

                .catch { error ->
                    Log.i(APP_TAG, "${error.message}" )
                    _screenState.send(StateResponse.StateError) }

                .collect { _screenState.send(StateResponse.StateSuccess) }
        }
    }

    companion object {
        const val APP_TAG = "tag"
    }
}