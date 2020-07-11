package com.zistus.basemvi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zistus.basemvi.model.Number
import com.zistus.basemvi.ui.main.state.MainStateEvent
import com.zistus.basemvi.ui.main.state.MainViewState

class MainViewModel : ViewModel() {
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations.switchMap(_stateEvent) { stateEvent ->
        stateEvent?.let {
            handleStateEvent(it)
        }
    }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        println("DEBUG: New StateEvent detected: $stateEvent")

        when (stateEvent) {
            is MainStateEvent.GetRandomNumberInfo -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        value = MainViewState(
                            randomNumberInfo = Number()
                        )
                    }
                }
            }

            is MainStateEvent.GetDateNumberInfo -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        value = MainViewState(
                            dateNumberInfo = Number()
                        )
                    }
                }
            }

            is MainStateEvent.GetTriviaNumberInfo -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        value = MainViewState(
                            triviaNumberInfo = Number()
                        )
                    }
                }
            }

            is MainStateEvent.GetYearNumberInfo -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        value = MainViewState(
                            yearNumberInfo = Number()
                        )
                    }
                }
            }
        }
    }

    fun setRandomNumber(number: Number) {
        val mViewState = viewState.value ?: MainViewState()
        mViewState.randomNumberInfo = number
        _viewState.value = mViewState
    }

    fun setYear(date: Number) {
        val mViewState = viewState.value ?: MainViewState()
        mViewState.randomNumberInfo = date
        _viewState.value = mViewState
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }
}