package com.zistus.basemvi.note.data

import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.NoteViewState
import com.zistus.core.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object NoteRepository {

    fun fetchAllNotes(): Flow<DataState<NoteViewState>> {
        return flow {
            emit(DataState.loading(true))
            val list = mutableListOf(NoteEntity.Note("01", "title", "content"))
            emit(DataState.data("Success", NoteViewState(noteList = list)))
        }
    }

    fun fetchNote(noteId: String?): Flow<DataState<NoteViewState>> {
        return flow {
            val note = noteId?.let { NoteEntity.Note(it, "title", "content") }
            emit(DataState.data("Success", NoteViewState(note = note)))
        }
    }

    fun saveNote(note: NoteEntity.Note?): Flow<DataState<NoteViewState>> {
        return flow {
            emit(DataState.loading(true))
            val dataState = DataState.data("Success", NoteViewState(note = note))
            emit(dataState)
        }
    }

    fun updateNote(note: NoteEntity.Note?): Flow<DataState<NoteViewState>> {
        return flow {
            val dataState = DataState.data("Success", NoteViewState(note = note))
            emit(dataState)
        }
    }
}