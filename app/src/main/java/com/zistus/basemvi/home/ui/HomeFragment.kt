package com.zistus.basemvi.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zistus.basemvi.R
import com.zistus.basemvi.utils.DataStateListener
import com.zistus.core.di.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var testText: String

    private val viewModel: HomeViewModel by viewModels<HomeViewModel> { viewModelFactory }
    private lateinit var dataStateListener: DataStateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateListener = context as DataStateListener
        } catch (e: ClassCastException) {
            println("$context must implement DataStateListener")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            // Handle Loading and Message
            dataStateListener.onDataStateChange(dataState)

            // handle Data<T>
            dataState.data?.let { event ->
                event.getContentIfNotHandled()?.let { mainViewState ->

                    println("DEBUG: com.zistus.core.utils.DataState: $mainViewState")

                    mainViewState.fileList?.let {
                        // set BlogPosts data
                        viewModel.setFileList(it)
                    }

                    mainViewState.user?.let {
                        // set User data
//                        viewModel.setUser(it)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            viewState.user?.let {
                // set BlogPosts to RecyclerView
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.fileList}")
            }

            viewState.user?.let {
                // set User data to widgets
                println("DEBUG: Setting User data: ${viewState.user}")
            }
        })
    }

//    private fun daggerComponent(kClass: Class<AppModuleDependencies>): AppComponent.Builder {
//        return DaggerAppComponent.builder()
//            .context(requireContext())
//            .appDependencies(EntryPointAccessors.fromApplication(requireContext(), kClass))
//    }
}