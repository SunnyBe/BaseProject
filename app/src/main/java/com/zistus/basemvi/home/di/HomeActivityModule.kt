package com.zistus.basemvi.home.di

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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HomeActivityModule {
    @Provides
    fun provideTestString() = "This is a test string"

    @Provides
    fun provideTestDao(appDatabase: AppDatabase): TestDao {
        return appDatabase.testDao()
    }

    @Provides
    fun provideTestNetworkSource(apiSource: ApiService): TestNetworkSource {
        return TestNetworkSourceImpl(apiSource)
    }

    @Provides
    fun provideTestDatabaseSource(testDao: TestDao): TestDatabaseSource {
        return TestDatabaseSourceImpl(testDao)
    }

    @Provides
    fun provideTestRepository(
        apiSource: TestNetworkSource,
        databaseSource: TestDatabaseSource
    ): TestRepository {
        return TestRepositoryImpl(apiSource, databaseSource)
    }

    @Provides
    fun provideTestUseCase(testRepository: TestRepository): TestUseCase {
        return TestUseCase(testRepo = testRepository)
    }
}