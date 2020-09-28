package com.zistus.basemvi.home.data.sources.cache

import com.zistus.basemvi.home.data.sources.cache.data.TestData

interface TestDatabaseSource {
    suspend fun testFiles(): List<TestData>
    suspend fun saveTestFile(testData: TestData)
    suspend fun saveTestFiles(testData: List<TestData>)
}