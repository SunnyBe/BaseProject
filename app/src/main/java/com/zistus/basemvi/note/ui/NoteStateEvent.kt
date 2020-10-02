package com.zistus.basemvi.note.ui

import com.zistus.basemvi.note.model.NoteEntity

sealed class NoteStateEvent {

    class Idle() : NoteStateEvent()

    class FetchAllNote() : NoteStateEvent()

    class GetNote(
        val noteId: String
    ) : NoteStateEvent()

    class SaveNote(
        val note: NoteEntity.Note?
    ) : NoteStateEvent()

    class UpdateNote(
        val note: NoteEntity.Note? = null,
        val noteId: String? = null
    ) : NoteStateEvent()
}