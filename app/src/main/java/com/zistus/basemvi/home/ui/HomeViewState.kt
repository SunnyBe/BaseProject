package com.zistus.basemvi.home.ui

import com.zistus.basemvi.home.data.sources.network.entity.TestEntity

data class HomeViewState(
    val fileList: List<TestEntity>? = null,
    val user: String? = null
)