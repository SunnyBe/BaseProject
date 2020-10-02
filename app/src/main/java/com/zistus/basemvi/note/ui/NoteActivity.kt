package com.zistus.basemvi.note.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zistus.basemvi.R
import com.zistus.basemvi.home.ui.HomeFragment
import com.zistus.basemvi.note.ui.note_list.NoteListFragment
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity(R.layout.activity_note) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var testText: String

    private val viewModel: NoteActivityViewModel by viewModels { viewModelFactory }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showNoteFragment()
        testTextLabel?.text = testText

//        viewModel.dataState.observe(this, Observer { dataState ->
//            dataState.loading.let {
//                Log.d(this.javaClass.simpleName, "Loading...: $it")
//            }
//
//            dataState.data?.let { event ->
//                event.getContentIfNotHandled()?.let {
//                    viewModel.setViewState(it)
//                }
//            }
//        })
//
//        viewModel.viewState.observe(this, Observer { viewState ->
//            viewState.note?.let {
//                Log.d(this.javaClass.simpleName, "Returned Note: $it")
//            }
//
//            viewState.noteList?.let {
//                Log.d(this.javaClass.simpleName, "Returned Note list: $it")
//            }
//        })

        viewModel.isLoading.observe(this, Observer {
            showProgress(it)
        })
    }

    private fun showProgress(toShow: Boolean) {
        if (toShow) {
            note_progress?.visibility = View.VISIBLE
        } else {
            note_progress?.visibility = View.GONE
        }
    }

    //Todo Remove when nav arch is in place
    private fun showNoteFragment() {
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.note_container, NoteListFragment(), "NoteFragment")
                .commit()
        }
    }
}