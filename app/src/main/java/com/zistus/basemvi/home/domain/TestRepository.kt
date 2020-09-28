package com.zistus.basemvi.home.domain

import androidx.lifecycle.LiveData
import com.zistus.basemvi.home.ui.HomeViewState
import com.zistus.core.utils.DataState

interface TestRepository {
    fun testFiles(): LiveData<DataState<HomeViewState>>
}