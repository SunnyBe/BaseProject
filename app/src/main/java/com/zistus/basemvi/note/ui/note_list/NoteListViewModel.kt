package com.zistus.basemvi.note.ui.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zistus.basemvi.note.data.NoteRepository
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.NoteStateEvent
import com.zistus.basemvi.note.ui.NoteViewState
import com.zistus.core.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NoteListViewModel
@Inject
constructor(private val noteRepository: NoteRepository):ViewModel() {

    private val intentChannel = ConflatedBroadcastChannel<NoteStateEvent>()

    private val stateChannel = ConflatedBroadcastChannel<NoteViewState>()


    val viewState =
        stateChannel.asFlow()
            .asLiveData()


    val dataState = intentChannel.asFlow()
        .flatMapLatest { stateEvent ->
            handleStateEvent(stateEvent)
        }.asLiveData()

    private fun handleStateEvent(stateEvent: NoteStateEvent): Flow<DataState<NoteViewState>> {
        return when (stateEvent) {
            is NoteStateEvent.FetchAllNote -> {
                noteRepository.fetchAllNotes()
            }

            is NoteStateEvent.GetNote -> {
                noteRepository.fetchNote(stateEvent.noteId)
            }

            is NoteStateEvent.UpdateNote -> {
                val note = stateEvent.note
                noteRepository.updateNote(note)
            }

            is NoteStateEvent.SaveNote -> {
                val note = stateEvent.note
                noteRepository.saveNote(note)
            }

            is NoteStateEvent.Idle -> {
                flow { emit(DataState.data("No Data", NoteViewState())) }
            }
        }
    }


    fun loadAllNotes() {
        intentChannel.offer(NoteStateEvent.FetchAllNote())
    }


    fun saveNote(note: NoteEntity.Note? = NoteEntity.Note("02", "test title", "test content")) {
        intentChannel.offer(NoteStateEvent.SaveNote(note))
    }


    fun setViewState(viewState: NoteViewState) {
        stateChannel.offer(viewState)
    }
}