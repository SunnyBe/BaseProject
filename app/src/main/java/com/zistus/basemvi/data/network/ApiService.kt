package com.zistus.basemvi.data.network

import androidx.lifecycle.LiveData
import com.zistus.basemvi.model.Number
import com.zistus.core.utils.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random/trivia")
    fun randomNumber(
        @Query("min") min: Long ?= 0,
        @Query("max") max: Long ?= 100,
        @Query("json") json: Boolean ?= true
    ): LiveData<GenericApiResponse<Number>>
}