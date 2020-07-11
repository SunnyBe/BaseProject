package com.zistus.basemvi.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zistus.basemvi.R
import com.zistus.basemvi.ui.main.state.MainStateEvent
import com.zistus.core.utils.DataStateListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.pane_dashboard_random.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    lateinit var dataStateListener: DataStateListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()

        randomDashboardPane?.setOnClickListener {
            viewModel.setStateEvent(MainStateEvent.GetRandomNumberInfo())
        }

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState->
            println("DEBUG: DataState: $dataState")
            // Handle Loading and Message
            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event->
                event.getContentIfNotHandled()?.let {
                    it.randomNumberInfo.let { number ->
                        // set the random number info returned
                        viewModel.setRandomNumber(number)
                    }

                    it.dateNumberInfo?.let {  date->
                        viewModel.setYear(date)
                    }
                }
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState->
            println("DEBUG: ViewState updated: $viewState")

            viewState.randomNumberInfo?.let {
                // Todo Update the view
                println("DEBUG: Setting random number info to view: ${viewState.randomNumberInfo}")
                randomNumberLabel.text = viewState.randomNumberInfo?.number.toString()
                randomNumberInfoLabel.text = viewState.randomNumberInfo?.text.toString()
            }

            viewState.dateNumberInfo?.let {
                // Todo Update the view
                println("DEBUG: Setting date info ot view: ${viewState.dateNumberInfo}")
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            dataStateListener = context as DataStateListener
        }catch(e: ClassCastException){
            println("$context must implement DataStateListener")
        }

    }
}