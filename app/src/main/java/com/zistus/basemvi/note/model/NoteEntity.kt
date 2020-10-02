package com.zistus.basemvi.note.model

sealed class NoteEntity {
    data class Note(
        val id: String,
        val title: String,
        val content: String
    ): NoteEntity()
}