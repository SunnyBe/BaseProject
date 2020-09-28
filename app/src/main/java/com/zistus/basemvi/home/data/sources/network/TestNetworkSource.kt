package com.zistus.basemvi.home.data.sources.network

import androidx.lifecycle.LiveData
import com.zistus.basemvi.home.data.sources.network.entity.TestDto
import com.zistus.core.utils.GenericApiResponse

interface TestNetworkSource {

    fun apiTestList(): LiveData<GenericApiResponse<List<TestDto>>>

//    suspend fun cApiTestList(): com.zistus.core.utils.DataState<List<TestDto>>
}