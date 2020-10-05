package com.zistus.basemvi.note.data

import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.NoteViewState
import com.zistus.core.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object NoteRepository {

    fun fetchAllNotes(): Flow<DataState<NoteViewState>> {
        return flow {
            emit(DataState.loading(true))
            val item = NoteEntity.Note("01", "first title", "content")
            val item1 = NoteEntity.Note("02", "test title", "this is just a test content for the show")
            val item2 = NoteEntity.Note("03", "another title", "content for the show, dont mind this isa tes")
            val item3 = NoteEntity.Note("04", "another title", "content for the show, dont mind this isa tes")
            val list = mutableListOf(item).apply {
                add(item1)
                add(item2)
                add(item3)
            }
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