package com.zistus.basemvi.note.ui

import com.zistus.basemvi.note.model.NoteEntity

data class NoteViewState(
    val noteList: List<NoteEntity.Note>? = null,
    val note: NoteEntity.Note? = null
)