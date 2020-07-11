package com.zistus.basemvi.di

import com.zistus.basemvi.data.MainRepositoryImpl
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.domain.repository.MainRepository
import com.zistus.core.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://numbersapi.p.rapidapi.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    fun provideInterceptor(): OkHttpClient {
        val okhttpBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okhttpBuilder.addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("x-rapidapi-key", "z5JQBIuHolmshTF2hiYHGMbLih8Qp1pHlv1jsn7qV4A49I2Nno")
            chain.proceed(builder.build())
        }
        okhttpBuilder.addInterceptor(loggingInterceptor)
        return okhttpBuilder.build()
    }

    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService =
        retrofitBuilder.build().create(ApiService::class.java)

    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository =
        MainRepositoryImpl(apiService)
}