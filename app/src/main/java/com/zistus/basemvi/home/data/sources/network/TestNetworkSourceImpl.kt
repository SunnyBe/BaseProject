package com.zistus.basemvi.home.data.sources.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.home.data.sources.network.entity.TestDto
import com.zistus.basemvi.home.data.sources.network.entity.TestEntity
import com.zistus.core.utils.GenericApiResponse
import javax.inject.Inject

class TestNetworkSourceImpl @Inject constructor(
    private val apiService: ApiService
): TestNetworkSource {

    override fun apiTestList(): LiveData<GenericApiResponse<List<TestDto>>> {
        val data =  MutableLiveData<List<String>>().apply {
            postValue(mutableListOf("Strength", "Money"))
        }
        return apiService.apiTestList()
    }

}