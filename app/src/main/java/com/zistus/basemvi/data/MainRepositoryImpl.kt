package com.zistus.basemvi.data

import androidx.lifecycle.LiveData
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.domain.repository.MainRepository
import com.zistus.basemvi.model.Number
import com.zistus.basemvi.ui.main.state.MainViewState
import kotlin.random.Random

class MainRepositoryImpl(val apiService: ApiService): MainRepository {

    override fun randomNumberDetails(): LiveData<MainViewState> {
        return object : LiveData<MainViewState>() {
            override fun onActive() {
                super.onActive()
                value = MainViewState(
                    randomNumberInfo = Number(number = Random.nextLong(0, 1000))
                )
            }
        }
    }

    override fun dateNumberDetails(): LiveData<MainViewState> {
        return object : LiveData<MainViewState>() {
            override fun onActive() {
                super.onActive()
                value = MainViewState(
                    dateNumberInfo = Number()
                )
            }
        }
    }

    override fun triviaNumberDetails(): LiveData<MainViewState> {
        return object : LiveData<MainViewState>() {
            override fun onActive() {
                super.onActive()
                value = MainViewState(
                    triviaNumberInfo = Number()
                )
            }
        }
    }

    override fun yearNumberDetails(): LiveData<MainViewState> {
        return object : LiveData<MainViewState>() {
            override fun onActive() {
                super.onActive()
                value = MainViewState(
                    yearNumberInfo = Number()
                )
            }
        }
    }
}