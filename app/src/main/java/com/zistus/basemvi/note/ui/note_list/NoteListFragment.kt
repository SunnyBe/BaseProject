package com.zistus.basemvi.note.ui.note_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zistus.basemvi.R
import com.zistus.basemvi.databinding.FragmentHomeBinding
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.basemvi.note.ui.NoteActivityViewModel
import com.zistus.basemvi.note.ui.note_detail.NoteUpdateDialog
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Shared view-model attached to activity
    private val activityViewModel: NoteActivityViewModel by activityViewModels()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @ExperimentalCoroutinesApi
    private val viewModel: NoteListViewModel by viewModels { viewModelFactory }

    private val noteListAdapter = NoteListAdapter(object : NoteListAdapter.ItemEvent {
        override fun itemClick(note: NoteEntity.Note) {
            val detailAction =
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note)
            findNavController().navigate(detailAction)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
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
                binding.listTitile.text = it.toString()
                val detailAction =
                    NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(it)
                findNavController().navigate(detailAction)
            }

            viewState.noteList?.let {
                Log.d(this.javaClass.simpleName, "Returned Note list: $it")
                binding.noteSwipeRefresh.isRefreshing = false
                binding.listTitile.text = "Total note ${it.size}"
                noteListAdapter.submitList(it)
            }
        })

        binding.bottomAppBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.note_menu_home -> {
                    if (findNavController().currentDestination?.id != R.id.noteListFragment) {
                        findNavController().navigate(R.id.noteListFragment)
                    }
                    true
                }
                R.id.note_menu_calendar -> {
                    // Respond to navigation item 2 reselection
                    true
                }
                R.id.note_menu_new -> {
                    // Handle new icon press
                    selectInputMethod(viewModel)
                    true
                }
                R.id.note_menu_file -> {
                    // Respond to navigation item 2 reselection
                    true
                }
                R.id.note_menu_user -> {
                    // Respond to navigation item 1 reselection
                    true
                }
                else -> false
            }
        }

        binding.noteSwipeRefresh.setOnRefreshListener{
            viewModel.loadAllNotes()
        }
    }

    @ExperimentalCoroutinesApi
    private fun selectInputMethod(viewModel: NoteListViewModel) {
        val items = arrayOf("Keyboard", "ImageToText", "VoiceToText")

        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.input_type_title))
            .setItems(items) { dialog, which ->
                // Respond to item chosen
                when (which) {
                    0 -> {
                        presentNewLoanDialog(viewModel)
                    }
                    1 -> {
                        Toast.makeText(context, R.string.not_available_label, Toast.LENGTH_LONG)
                            .show()
                    }

                    2 -> {
                        Toast.makeText(context, R.string.not_available_label, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            .show()
    }

    @ExperimentalCoroutinesApi
    private fun presentNewLoanDialog(viewModel: NoteListViewModel) {
        val onUpdateProcess = object : NoteUpdateDialog.OnUpdateProcess {
            override fun updated(dialog: NoteUpdateDialog) {
                dialog.dismiss()
            }
        }
        val noteDetailDialog =
            NoteUpdateDialog(viewModel = viewModel, onUpdateProcess = onUpdateProcess)
        activity?.apply {
            noteDetailDialog.show(supportFragmentManager, NoteUpdateDialog.TAG)
            noteDetailDialog.isCancelable = false
        }
    }

    private fun setupView(view: View) {
        binding.noteList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = noteListAdapter
        }
    }


}