package com.zistus.basemvi.home.data.repository

import androidx.lifecycle.LiveData
import com.zistus.basemvi.home.data.sources.cache.TestDatabaseSource
import com.zistus.basemvi.home.data.sources.network.TestNetworkSource
import com.zistus.basemvi.home.data.sources.network.entity.TestDto
import com.zistus.basemvi.home.domain.TestRepository
import com.zistus.basemvi.home.ui.HomeViewState
import com.zistus.core.repository.NetworkBoundResource
import com.zistus.core.utils.ApiSuccessResponse
import com.zistus.core.utils.DataState
import com.zistus.core.utils.GenericApiResponse
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val apiSource: TestNetworkSource,
    private val dbSource: TestDatabaseSource
) : TestRepository {
    override fun testFiles(): LiveData<DataState<HomeViewState>>{

        return object : NetworkBoundResource<List<TestDto>, HomeViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<TestDto>>) {
//                dbSource.saveTestFiles(response.body.map { it.toEntity() }.map { it.toData() })
                result.value = DataState.data(
                    message = "Successful",
                    data = HomeViewState(
                        fileList = response.body.map { it.toEntity() }
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<TestDto>>> {
                return apiSource.apiTestList()
            }

        }.asLiveData()
    }
}