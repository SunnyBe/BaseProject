package com.zistus.basemvi.domain.repository

import androidx.lifecycle.LiveData
import com.zistus.basemvi.ui.main.state.MainViewState
import com.zistus.core.utils.DataState

interface MainRepository {
    fun randomNumberDetails(): LiveData<DataState<MainViewState>>
    fun dateNumberDetails(): LiveData<DataState<MainViewState>>
    fun triviaNumberDetails(): LiveData<DataState<MainViewState>>
    fun yearNumberDetails(): LiveData<DataState<MainViewState>>
}