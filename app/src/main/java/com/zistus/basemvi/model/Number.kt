package com.zistus.basemvi.model

import java.util.*

data class Number(
    val id: String ?= UUID.randomUUID().toString(),
    val number: Long ?= 0,
    val text: String ?= "Nothing to show",
    val found: Boolean ?= false,
    val type: String ?= NumberEnum.RANDOM.toString().toLowerCase()
)