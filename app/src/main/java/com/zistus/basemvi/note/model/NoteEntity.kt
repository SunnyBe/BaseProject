package com.zistus.basemvi.note.model

import com.zistus.core.utils.DateUtils
import java.io.Serializable
import java.util.*

sealed class NoteEntity {

    data class Note(
        val id: String?=UUID.randomUUID().toString(),
        val title: String,
        val content: String? = "",
        val date: String? = DateUtils.todayDateIsoFormat()
    ) : NoteEntity(), Serializable
}