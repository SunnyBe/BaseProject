package com.zistus.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.zistus.core.utils.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class DataBoundResource<RequestObject, ResponseObject> {

    protected val result = MediatorLiveData<DataState<ResponseObject>>()

    init {
        // For every call set initial value to loading
        result.value = DataState.loading(true)

        GlobalScope.launch(IO) {
            withContext(Main) {
                val loadFromDb = loadFromDb()
                // Listen for apiResponse once and remove the source from mediator liveData result
                result.addSource(loadFromDb) { response ->
                    result.removeSource(loadFromDb)
                    if (shouldFetch(response)) {
                        fetchFromNetwork(loadFromDb)
                    } else {
                        processDbResponse(response)
                    }
                }
            }
        }
    }

    fun handleNetworkCall(response: GenericApiResponse<RequestObject>) {

        when (response) {
            is ApiSuccessResponse -> {
                saveNewData(processResponse(response))
            }
            is ApiErrorResponse -> {
                println("DEBUG: NetworkBoundResource: ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                println("DEBUG: NetworkBoundResource: Request returned NOTHING (HTTP 204)")
                onReturnError("HTTP 204. Returned NOTHING.")
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResponseObject>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(apiResponse) { newData ->
            setValue(DataState.loading(true))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            handleNetworkCall(response)
        }
    }

    fun onReturnError(message: String) {
        result.value = DataState.error(message)
    }

    fun setValue(newValue: DataState<ResponseObject>) {
        result.value = newValue
    }

    protected open fun processResponse(response: ApiSuccessResponse<RequestObject>) = response.body

    abstract fun saveNewData(response: RequestObject)

    abstract fun createCall(): LiveData<GenericApiResponse<RequestObject>>

    abstract fun loadFromDb(): LiveData<ResponseObject>

    abstract fun shouldFetch(data: ResponseObject?): Boolean

    abstract fun processDbResponse(response: ResponseObject)

    fun asLiveData() = result as LiveData<DataState<ResponseObject>>
}
