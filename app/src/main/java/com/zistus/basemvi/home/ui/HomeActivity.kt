package com.zistus.basemvi.home.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zistus.basemvi.R
import com.zistus.basemvi.utils.DataStateListener
import com.zistus.core.utils.DataState

class HomeActivity: AppCompatActivity(R.layout.activity_home), DataStateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        dataState?.loading?.let { loading->
            if (loading) {
                Toast.makeText(this, "loading ...", Toast.LENGTH_LONG).show()
            }
        }
    }
}