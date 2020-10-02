package com.zistus.basemvi.note.ui.note_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zistus.basemvi.R
import com.zistus.basemvi.note.ui.NoteActivityViewModel
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment: Fragment(R.layout.fragment_home) {

    // Shared view-model attached to activity
    private val activityViewModel: NoteActivityViewModel by activityViewModels()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var testText: String

    @ExperimentalCoroutinesApi
    private val viewModel: NoteListViewModel by viewModels { viewModelFactory }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView2?.text = testText
        textView2?.setOnClickListener {
            viewModel.loadAllNotes()
//            viewModel.saveNote()
        }

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
                textView2?.text = it.toString()
            }
        })
    }
}