package com.zistus.basemvi.domain.repository

import androidx.lifecycle.LiveData
import com.zistus.basemvi.ui.main.state.MainViewState

interface MainRepository {
    fun randomNumberDetails(): LiveData<MainViewState>
    fun dateNumberDetails(): LiveData<MainViewState>
    fun triviaNumberDetails(): LiveData<MainViewState>
    fun yearNumberDetails(): LiveData<MainViewState>
}