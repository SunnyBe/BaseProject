package com.zistus.basemvi.note.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.core.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteActivityViewModel @Inject constructor(

) : ViewModel() {

    val intentChannel = ConflatedBroadcastChannel<NoteStateEvent>()
    val stateChannel = ConflatedBroadcastChannel<NoteViewState>()

    val viewState =
        stateChannel.asFlow()
            .asLiveData()

    val dataState = intentChannel.asFlow()
        .flatMapLatest { stateEvent ->
            Log.d(this.javaClass.simpleName, "Triggered: dataState: LiveData()")
            handleStateEvent(stateEvent)
        }.asLiveData()


    init {
//        intentChannel.asFlow()
//            .flatMapLatest {  stateEvent->
//                handleStateEvent(stateEvent)
//            }
//            .launchIn(viewModelScope)
    }

    private fun handleStateEvent(stateEvent: NoteStateEvent): Flow<DataState<NoteViewState>> {
        return when (stateEvent) {
            is NoteStateEvent.FetchAllNote -> {
                Log.d(this.javaClass.simpleName, "Triggered: handleStateEvent()")
                flow {
                    val list = mutableListOf(NoteEntity.Note("01", "title", "content"))
                    emit(DataState.data("Success", NoteViewState(noteList = list)))
                }
            }

            is NoteStateEvent.GetNote -> {
                flow {
                    val note = NoteEntity.Note("01", "title", "content")
                    emit(DataState.data("Success" ,NoteViewState(note = note)))
                }
            }

            is NoteStateEvent.UpdateNote-> {
                flow {
                    val note = NoteEntity.Note("01", "title", "content")
                    val dataState = DataState.data("Success", NoteViewState(note = note))
                    emit(dataState)
                }
            }

            is NoteStateEvent.SaveNote-> {
                flow {
                    val note = NoteEntity.Note("05", "saved title", "saved content")
                    val dataState = DataState.data("Success", NoteViewState(note = note))
                    emit(dataState)
                }
            }

            is NoteStateEvent.Idle -> {
                flow { emit(DataState.data("No Data",NoteViewState())) }
            }
        }
    }

    fun loadAllNotes() {
        Log.d(this.javaClass.simpleName, "Triggered: loadAllNotes()")
        intentChannel.offer(NoteStateEvent.FetchAllNote())
    }

    fun saveNote(note: NoteEntity.Note? = NoteEntity.Note("02", "test title", "test content")) {
        intentChannel.offer(NoteStateEvent.SaveNote(note))
    }

    fun setViewState(viewState: NoteViewState) {
        stateChannel.offer(viewState)
    }
}