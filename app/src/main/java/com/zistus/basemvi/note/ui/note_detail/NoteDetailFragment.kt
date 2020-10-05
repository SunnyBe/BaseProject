package com.zistus.basemvi.note.ui.note_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zistus.basemvi.R
import com.zistus.basemvi.databinding.FragmentHomeBinding
import com.zistus.basemvi.databinding.FragmentNoteDetailBinding
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.NoteActivityViewModel
import com.zistus.basemvi.note.ui.note_list.NoteListAdapter
import com.zistus.basemvi.note.ui.note_list.NoteListViewModel
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class NoteDetailFragment: Fragment(R.layout.fragment_note_detail) {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    // Shared view-model attached to activity
    private val activityViewModel: NoteActivityViewModel by activityViewModels()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @ExperimentalCoroutinesApi
    private val viewModel: NoteListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadAllNotes()

        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            activityViewModel.dataStateChanged(dataState)
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let {
                    viewModel.setViewState(it)
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.note?.let {
                Log.d(this.javaClass.simpleName, "Returned Note: $it")
                populateNoteDetailOnView(it)
            }
        })
    }

    private fun populateNoteDetailOnView(noteDetail: NoteEntity.Note) {

    }
}