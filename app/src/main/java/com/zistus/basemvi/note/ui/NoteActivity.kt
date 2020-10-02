package com.zistus.basemvi.note.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zistus.basemvi.R
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity(R.layout.activity_note) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var testText: String

    private val viewModel: NoteActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testTextLabel?.text = testText
        testTextLabel?.setOnClickListener {
            viewModel.loadAllNotes()
            viewModel.saveNote()
        }

        viewModel.dataState.observe(this, Observer { dataState->
            dataState.loading.let {
                Log.d(this.javaClass.simpleName, "Loading...: $it")
            }

            dataState.data?.let { event->
                event.getContentIfNotHandled()?.let {
                    Log.d(this.javaClass.simpleName, "Returned Note list: $it")
                    viewModel.setViewState(it)
                }
            }
        })

        viewModel.viewState.observe(this, Observer { viewState->
            viewState.note?.let {
                Log.d(this.javaClass.simpleName, "Returned Note: $it")
            }

            viewState.noteList?.let {
                Log.d(this.javaClass.simpleName, "Returned Note list: $it")
            }
        })
    }
}