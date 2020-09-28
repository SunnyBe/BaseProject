package com.zistus.basemvi.home.data.sources.cache

import com.zistus.basemvi.home.data.sources.cache.data.TestDao
import com.zistus.basemvi.home.data.sources.cache.data.TestData
import javax.inject.Inject

class TestDatabaseSourceImpl @Inject constructor(
    private val testDao: TestDao
): TestDatabaseSource {

    override suspend fun testFiles(): List<TestData> {
        return testDao.selectAll()
    }

    override suspend fun saveTestFile(testData: TestData) {
        return testDao.insert(testData)
    }

    override suspend fun saveTestFiles(testDatas: List<TestData>) {
        return testDao.insert(testData = testDatas)
    }
}