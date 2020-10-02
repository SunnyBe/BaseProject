package com.zistus.basemvi.note.ui

import com.zistus.basemvi.note.model.NoteEntity

sealed class NoteStateEvent {

    class Idle() : NoteStateEvent()

    class FetchAllNote() : NoteStateEvent()

    class GetNote(
        noteId: String
    ) : NoteStateEvent()

    class SaveNote(
        note: NoteEntity.Note?
    ) : NoteStateEvent()

    class UpdateNote(
        note: NoteEntity.Note? = null,
        noteId: String? = null
    ) : NoteStateEvent()
}