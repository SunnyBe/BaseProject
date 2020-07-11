package com.zistus.basemvi.ui.main.state

import com.zistus.basemvi.model.Number

data class MainViewState (
    var randomNumberInfo: Number? = null,
    var triviaNumberInfo: Number? = null,
    var yearNumberInfo: Number? = null,
    var dateNumberInfo: Number? = null
)