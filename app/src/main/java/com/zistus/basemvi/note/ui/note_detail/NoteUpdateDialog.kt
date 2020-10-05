package com.zistus.basemvi.note.ui.note_detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zistus.basemvi.R
import com.zistus.basemvi.databinding.DialogNoteUpdateBinding
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.note_list.NoteListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NoteUpdateDialog(
    private val viewModel: NoteListViewModel,
    private val note: NoteEntity.Note? = null,
    private val onUpdateProcess: OnUpdateProcess
) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "NoteUpdateDialog"
    }

    private var _binding: DialogNoteUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogNoteUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note?.let {
            prepopulateEntriesForUpdate(it)
        }

        binding.saveButton.setOnClickListener {
            // update or save the note detail
            val noteToSave = NoteEntity.Note(
                title = binding.noteTitleEntryValue.text.toString(),
                content = binding.noteContentEntryValue.text.toString()
            )
            viewModel.saveNote(noteToSave)
            onUpdateProcess.updated(this)
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun prepopulateEntriesForUpdate(note: NoteEntity.Note) {
        binding.noteContentEntryValue.setText(note.content)
        binding.noteTitleEntryValue.setText(note.title)
    }

    interface OnUpdateProcess {
        fun updated(dialog: NoteUpdateDialog)
    }

}