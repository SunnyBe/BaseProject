package com.zistus.basemvi.utils

import com.zistus.core.utils.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}