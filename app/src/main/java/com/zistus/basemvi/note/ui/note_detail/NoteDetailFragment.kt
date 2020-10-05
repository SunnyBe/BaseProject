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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zistus.basemvi.R
import com.zistus.basemvi.databinding.FragmentNoteDetailBinding
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.NoteActivityViewModel
import com.zistus.basemvi.note.ui.note_list.NoteListViewModel
import com.zistus.core.di.ViewModelFactory
import com.zistus.core.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

    // Shared view-model attached to activity
    private val activityViewModel: NoteActivityViewModel by activityViewModels()
    private val args: NoteDetailFragmentArgs by navArgs()

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
        val note = args.NoteDetail
        populateNoteDetailOnView(note)

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

        binding.noteToolBar.setNavigationOnClickListener {
            // Handle navigation icon press
            findNavController().navigateUp()
        }

        binding.noteToolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_capture -> {
                    // Handle capture icon press
                    true
                }
                R.id.menu_update -> {
                    // Handle update icon press
                    val onUpdateProcess = object : NoteUpdateDialog.OnUpdateProcess {
                        override fun updated(dialog: NoteUpdateDialog) {
                            dialog.dismiss()
                        }
                    }
                    val noteDetailDialog =
                        NoteUpdateDialog(viewModel, note, onUpdateProcess)
                    activity?.apply {
                        noteDetailDialog.show(supportFragmentManager, NoteUpdateDialog.TAG)
                        noteDetailDialog.isCancelable = false
                    }
                    true
                }
                R.id.menu_settings -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }
    }

    private fun populateNoteDetailOnView(noteDetail: NoteEntity.Note?) {
        binding.detailContentLayout.detailDate.text = noteDetail?.date?.let { date ->
            DateUtils.fetchDateTimeFromIso(date, "dd MMM (hh:mma)")?.toLowerCase()
        }
        binding.detailContentLayout.detailTitle.text = noteDetail?.title
        binding.detailContentLayout.detailContent.text = noteDetail?.content
        binding.detailContentLayout.noteDetailLocation.text = "Lagos, Nigeria"
    }
}