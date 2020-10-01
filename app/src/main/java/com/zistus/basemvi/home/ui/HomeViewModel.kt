package com.zistus.basemvi.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zistus.basemvi.home.data.sources.network.entity.TestEntity
import com.zistus.basemvi.home.domain.TestUseCase
import com.zistus.core.utils.AbsentLiveData
import com.zistus.core.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val testUseCase: TestUseCase
) : ViewModel() {
    private val _stateEvent: MutableLiveData<HomeStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<HomeViewState> = MutableLiveData()

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val viewState: LiveData<HomeViewState>
        get() = _viewState


    val dataState: LiveData<DataState<HomeViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                processStateEvent(stateEvent)
            }
        }

    val dataStateFlow = flow<DataState<HomeViewState>> {
        _stateEvent.value
    }

    private fun processStateEvent(stateEvent: HomeStateEvent?): LiveData<DataState<HomeViewState>>? {
        return when (stateEvent) {
            is HomeStateEvent.FetchFilesEvent -> {
                testUseCase.testFiles()
            }

            is HomeStateEvent.GetUserEvent -> {
                AbsentLiveData.create()
            }
            else -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setFileList(files: List<TestEntity>) {
        _viewState.value = HomeViewState(files)
    }

    fun getCurrentViewStateOrNew(): HomeViewState {
        val value = viewState.value?.let {
            it
        } ?: HomeViewState()
        return value
    }

    fun setStateEvent(event: HomeStateEvent) {
        val state: HomeStateEvent = event
        _stateEvent.value = state
    }

}