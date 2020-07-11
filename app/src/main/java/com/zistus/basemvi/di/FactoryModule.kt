package com.zistus.basemvi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zistus.basemvi.ui.main.MainViewModel
import com.zistus.core.di.module.ViewModelFactory
import com.zistus.core.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class FactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}