package com.zistus.basemvi.home.data.sources.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.home.data.sources.network.entity.TestDto
import com.zistus.basemvi.home.data.sources.network.entity.TestEntity
import com.zistus.core.utils.DataState
import com.zistus.core.utils.GenericApiResponse
import javax.inject.Inject

class TestNetworkSourceImpl @Inject constructor(
    private val apiService: ApiService
): TestNetworkSource {

    override fun apiTestList(): LiveData<GenericApiResponse<List<TestDto>>> {
        return apiService.apiTestList()
    }

//    override suspend fun cApiTestList(): com.zistus.core.utils.DataState<List<TestDto>> {
//        val apiCall = apiService.cApiTestList()
//        if (apiCall.isSuccessful) {
//            return com.zistus.core.utils.DataState.data(
//                message = "Successfully fetched",
//                data = apiCall.body()
//            )
//        } else {
//            com.zistus.core.utils.DataState.error<Throwable>(apiCall.errorBody())
//        }
//    }

}