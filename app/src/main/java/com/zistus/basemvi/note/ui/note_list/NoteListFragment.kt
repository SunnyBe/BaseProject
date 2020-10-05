package com.zistus.basemvi.note.ui.note_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zistus.basemvi.R
import com.zistus.basemvi.databinding.FragmentHomeBinding
import com.zistus.basemvi.note.ui.NoteActivityViewModel
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
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

    private val noteListAdapter = NoteListAdapter()

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
                textView2?.text = it.toString()
            }

            viewState.noteList?.let {
                Log.d(this.javaClass.simpleName, "Returned Note list: $it")
//                textView2?.text = it.toString()
                noteListAdapter.submitList(it)
            }
        })
    }

    private fun setupView(view: View) {
        binding.noteList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = noteListAdapter
        }
    }


}