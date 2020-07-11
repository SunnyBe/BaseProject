package com.zistus.basemvi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zistus.basemvi.R
import com.zistus.basemvi.ui.main.state.MainStateEvent
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.pane_dashboard_random.*

class MainFragment: Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()

        randomDashboardPane?.setOnClickListener {
            viewModel.setStateEvent(MainStateEvent.GetRandomNumberInfo())
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState->
            println("DEBUG: DataState: $dataState")

            // handle different data state that could return
            dataState.randomNumberInfo?.let { number ->
                // set the random number info returned
                viewModel.setRandomNumber(number)
            }

            dataState.dateNumberInfo?.let {  date->
                viewModel.setYear(date)
            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState->
            println("DEBUG: ViewState updated: $viewState")

            viewState.randomNumberInfo?.let {
                // Todo Update the view
                println("DEBUG: Setting random number info to view: ${viewState.randomNumberInfo}")
                randomNumberLabel.text = viewState.randomNumberInfo?.number.toString()
                randomNumberInfoLabel.text = viewState.randomNumberInfo?.content.toString()
            }

            viewState.dateNumberInfo?.let {
                // Todo Update the view
                println("DEBUG: Setting date info ot view: ${viewState.dateNumberInfo}")
            }
        })
    }
}