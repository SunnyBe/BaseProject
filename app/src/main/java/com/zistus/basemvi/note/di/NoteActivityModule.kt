package com.zistus.basemvi.note.di

import com.zistus.basemvi.note.data.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NoteActivityModule {

    @Provides
    fun provideNoteRepository(): NoteRepository = NoteRepository
}