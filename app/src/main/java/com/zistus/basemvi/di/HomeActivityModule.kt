package com.zistus.basemvi.di

import androidx.fragment.app.Fragment
import com.zistus.basemvi.home.ui.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object HomeActivityModule {
    @Provides
    fun provideTestString() = "This is a test string"
}