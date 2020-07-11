package com.zistus.basemvi.data

import androidx.lifecycle.LiveData
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.domain.repository.MainRepository
import com.zistus.basemvi.model.Number
import com.zistus.basemvi.ui.main.state.MainViewState
import com.zistus.core.repository.NetworkBoundResource
import com.zistus.core.utils.ApiSuccessResponse
import com.zistus.core.utils.DataState
import com.zistus.core.utils.GenericApiResponse

class MainRepositoryImpl(val apiService: ApiService) : MainRepository {

    override fun randomNumberDetails(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<Number, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<Number>) {
                result.value = DataState.data(
                    data = MainViewState(
                        dateNumberInfo = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<Number>> {
                return apiService.randomNumber()
            }

        }.asLiveData()
    }

    override fun dateNumberDetails(): LiveData<DataState<MainViewState>> {
        return object : LiveData<DataState<MainViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.data(
                    data = MainViewState(
                        dateNumberInfo = Number()
                    )
                )
            }
        }
    }

    override fun triviaNumberDetails(): LiveData<DataState<MainViewState>> {
        return object : LiveData<DataState<MainViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.data(
                    data = MainViewState(
                        triviaNumberInfo = Number()
                    )
                )
            }
        }
    }

    override fun yearNumberDetails(): LiveData<DataState<MainViewState>> {
        return object : LiveData<DataState<MainViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.data(
                    data = MainViewState(
                        yearNumberInfo = Number()
                    )
                )
            }
        }
    }
}