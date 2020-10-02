package com.zistus.basemvi.note.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.zistus.basemvi.R
import com.zistus.basemvi.databinding.ActivityNoteBinding
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: ActivityNoteBinding
    private val viewModel: NoteActivityViewModel by viewModels { viewModelFactory }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.note_host_container) as NavHostFragment? ?: return
        val navController = navHostFragment.navController

        viewModel.isLoading.observe(this, Observer {
            showProgress(it)
        })
    }

    private fun showProgress(toShow: Boolean) {
        if (toShow) {
            binding.noteProgress.visibility = View.VISIBLE
        } else {
            binding.noteProgress.visibility = View.GONE
        }
    }
}