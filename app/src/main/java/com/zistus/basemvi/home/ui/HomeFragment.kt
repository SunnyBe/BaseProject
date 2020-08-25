package com.zistus.basemvi.home.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zistus.basemvi.utils.DataStateListener

class HomeFragment: Fragment() {
    lateinit var viewModel: HomeViewModel
    lateinit var dataStateListener: DataStateListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            dataStateListener = context as DataStateListener
        }catch(e: ClassCastException){
            println("$context must implement DataStateListener")
        }

    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            // Handle Loading and Message
            dataStateListener.onDataStateChange(dataState)

            // handle Data<T>
            dataState.data?.let{ event ->
                event.getContentIfNotHandled()?.let{ mainViewState ->

                    println("DEBUG: DataState: ${mainViewState}")

                    mainViewState.fileList?.let{
                        // set BlogPosts data
                        viewModel.setFileList(it)
                    }

                    mainViewState.user?.let{
                        // set User data
                        viewModel.setUser(it)
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.user?.let {
                // set BlogPosts to RecyclerView
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.fileList}")
            }

            viewState.user?.let{
                // set User data to widgets
                println("DEBUG: Setting User data: ${viewState.user}")
            }
        })
    }
}