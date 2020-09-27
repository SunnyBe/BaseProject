package com.zistus.basemvi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zistus.basemvi.home.ui.HomeViewModel
import com.zistus.core.di.ViewModelFactory
import com.zistus.core.di.scopes.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
abstract class FactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}