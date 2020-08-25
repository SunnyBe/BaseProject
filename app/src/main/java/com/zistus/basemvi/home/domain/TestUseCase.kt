package com.zistus.basemvi.home.domain

import androidx.lifecycle.LiveData
import com.zistus.basemvi.home.ui.HomeViewState
import com.zistus.core.utils.DataState

class TestUseCase(
    private val testRepo: TestRepository
) {
    fun testFiles(): LiveData<DataState<HomeViewState>> {
        return testRepo.testFiles()
    }
}