package com.zistus.basemvi.home.data.sources.cache.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zistus.basemvi.utils.Labels

@Dao
interface TestDao {

    @Query("SELECT * from ${Labels.Database.TEST_DATA}")
    suspend fun selectAll(): List<TestData>

    @Query("SELECT * from ${Labels.Database.TEST_DATA} WHERE id = :key")
    suspend fun select(key: String): TestData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(testData: TestData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(testData: List<TestData>)
}