package com.zistus.basemvi.note.model

import com.zistus.core.utils.DateUtils

sealed class NoteEntity {
    data class Note(
        val id: String,
        val title: String,
        val content: String? = "",
        val date: String? = DateUtils.todayDateIsoFormat()
    ) : NoteEntity()
}