package com.zistus.basemvi.note.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zistus.basemvi.note.data.NoteRepository
import com.zistus.core.utils.DataState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class NoteActivityViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _isLoadingChannel = ConflatedBroadcastChannel<Boolean>(false)
    private val _alertLoadingChannel = ConflatedBroadcastChannel<String?>(null)

    val isLoading: LiveData<Boolean> = _isLoadingChannel.asFlow().asLiveData()

    fun dataStateChanged(dataState: DataState<*>) {
        _isLoadingChannel.offer(dataState.loading)
        _alertLoadingChannel.offer(dataState.message?.getContentIfNotHandled())
    }

//        @ExperimentalCoroutinesApi
//    val viewState =
//        stateChannel.asFlow()
//            .asLiveData()
//
//    val dataState = intentChannel.asFlow()
//        .flatMapLatest { stateEvent ->
//            handleStateEvent(stateEvent)
//        }.asLiveData()
//
//    private fun handleStateEvent(stateEvent: NoteStateEvent): Flow<DataState<NoteViewState>> {
//        return when (stateEvent) {
//            is NoteStateEvent.FetchAllNote -> {
//                noteRepository.fetchAllNotes()
//            }
//
//            is NoteStateEvent.GetNote -> {
//                noteRepository.fetchNote(stateEvent.noteId)
//            }
//
//            is NoteStateEvent.UpdateNote -> {
//                val note = stateEvent.note
//                noteRepository.updateNote(note)
//            }
//
//            is NoteStateEvent.SaveNote -> {
//                val note = stateEvent.note
//                noteRepository.saveNote(note)
//            }
//
//            is NoteStateEvent.Idle -> {
//                flow { emit(DataState.data("No Data", NoteViewState())) }
//            }
//        }
//    }
//
//    @ExperimentalCoroutinesApi
//    fun loadAllNotes() {
//        intentChannel.offer(NoteStateEvent.FetchAllNote())
//    }
//
//    @ExperimentalCoroutinesApi
//    fun saveNote(note: NoteEntity.Note? = NoteEntity.Note("02", "test title", "test content")) {
//        intentChannel.offer(NoteStateEvent.SaveNote(note))
//    }
//
//    @ExperimentalCoroutinesApi
//    fun setViewState(viewState: NoteViewState) {
//        stateChannel.offer(viewState)
//    }
}