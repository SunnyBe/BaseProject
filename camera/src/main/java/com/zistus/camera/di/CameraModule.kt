package com.zistus.camera.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CameraModule {

    @Provides
    fun provideTestString(): String = "This is a test injection"
}