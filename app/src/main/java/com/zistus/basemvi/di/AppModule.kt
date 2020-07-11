package com.zistus.basemvi.di

import com.zistus.basemvi.data.MainRepositoryImpl
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideRetrofitBuilder() =
        Retrofit.Builder()
            .baseUrl("https://numbersapi.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService =
        retrofitBuilder.build().create(ApiService::class.java)

    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository =
        MainRepositoryImpl(apiService)
}