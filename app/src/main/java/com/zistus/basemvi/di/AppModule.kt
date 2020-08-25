package com.zistus.basemvi.di

import android.content.Context
import androidx.room.Room
import com.zistus.basemvi.data.cache.room.AppDatabase
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.home.data.repository.TestRepositoryImpl
import com.zistus.basemvi.home.data.sources.cache.TestDatabaseSource
import com.zistus.basemvi.home.data.sources.cache.TestDatabaseSourceImpl
import com.zistus.basemvi.home.data.sources.cache.data.TestDao
import com.zistus.basemvi.home.data.sources.network.TestNetworkSource
import com.zistus.basemvi.home.data.sources.network.TestNetworkSourceImpl
import com.zistus.basemvi.home.domain.TestRepository
import com.zistus.basemvi.home.domain.TestUseCase
import com.zistus.core.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideTestString(): String = "This is a test injection"

    @Singleton
    @Provides
    fun provideApiService(apiService: ApiService): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://randomapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context, appDatabase: AppDatabase): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTestDao(appDatabase: AppDatabase): TestDatabaseSource {
        return TestDatabaseSourceImpl(appDatabase.testDao())
    }

    @Singleton
    @Provides
    fun provideTestNetworkSource(apiSource: ApiService): TestNetworkSource {
        return TestNetworkSourceImpl(apiSource)
    }

    @Singleton
    @Provides
    fun provideTestDatabaseSource(testDao: TestDao): TestDatabaseSource {
        return TestDatabaseSourceImpl(testDao)
    }

    @Singleton
    @Provides
    fun provideTestRepository(apiSource: TestNetworkSource, databaseSource: TestDatabaseSource): TestRepository {
        return TestRepositoryImpl(apiSource, databaseSource)
    }

    @Singleton
    @Provides
    fun provideTestUseCase(testRepository: TestRepository): TestUseCase {
        return TestUseCase(testRepo = testRepository)
    }
}