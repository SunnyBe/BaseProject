package com.zistus.basemvi.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zistus.basemvi.domain.repository.MainRepository
import com.zistus.basemvi.model.Number
import com.zistus.basemvi.ui.main.state.MainStateEvent
import com.zistus.basemvi.ui.main.state.MainViewState
import com.zistus.core.utils.AbsentLiveData
import com.zistus.core.utils.DataState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations.switchMap(_stateEvent) { stateEvent ->
        stateEvent?.let {
            handleStateEvent(it)
        }
    }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        println("DEBUG: New StateEvent detected: $stateEvent")

        when (stateEvent) {
            is MainStateEvent.GetRandomNumberInfo -> {
                return mainRepository.randomNumberDetails()
            }

            is MainStateEvent.GetDateNumberInfo -> {
                return mainRepository.dateNumberDetails()
            }

            is MainStateEvent.GetTriviaNumberInfo -> {
                return mainRepository.triviaNumberDetails()
            }

            is MainStateEvent.GetYearNumberInfo -> {
                return mainRepository.yearNumberDetails()
            }

            is MainStateEvent.None -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun setRandomNumber(number: Number?) {
        val mViewState = viewState.value ?: MainViewState()
        mViewState.randomNumberInfo = number
        _viewState.value = mViewState
    }

    fun setYear(date: Number?) {
        val mViewState = viewState.value ?: MainViewState()
        mViewState.randomNumberInfo = date
        _viewState.value = mViewState
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }

    fun test() = "Test string"
}