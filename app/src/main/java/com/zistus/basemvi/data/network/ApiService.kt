package com.zistus.basemvi.data.network

import androidx.lifecycle.LiveData
import com.zistus.basemvi.home.data.sources.network.entity.TestDto
import com.zistus.core.utils.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
    fun apiTestList(): LiveData<GenericApiResponse<List<TestDto>>>

//    @GET("6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
//    suspend fun cApiTestList(): Response<List<TestDto>>
}