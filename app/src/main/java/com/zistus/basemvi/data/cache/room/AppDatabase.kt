package com.zistus.basemvi.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zistus.basemvi.home.data.sources.cache.data.TestDao
import com.zistus.basemvi.home.data.sources.cache.data.TestData

@Database(
    entities = [
    TestData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun testDao(): TestDao
}