package com.zistus.basemvi.domain.repository

import androidx.lifecycle.LiveData
import com.zistus.basemvi.ui.main.state.MainViewState
import com.zistus.core.utils.DataState

interface MainRepository {
    fun randomNumberDetails(): LiveData<DataState<MainViewState>>
    fun dateNumberDetails(day: Long, month: Long): LiveData<DataState<MainViewState>>
    fun triviaNumberDetails(number: Long?=0): LiveData<DataState<MainViewState>>
    fun yearNumberDetails(year: Long): LiveData<DataState<MainViewState>>
    fun mathFact(number: Long): LiveData<DataState<MainViewState>>
}