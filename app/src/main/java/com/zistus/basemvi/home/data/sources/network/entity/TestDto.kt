package com.zistus.basemvi.home.data.sources.network.entity

data class TestDto(
    val first: String ?= null,
    val last: String ?= null,
    val email: String ?= null,
    val address: String ?= null,
    val created: String ?= null,
    val balance: String ?=null
) {
    fun toEntity() = TestEntity(
        first, last, email, address, created, balance
    )
}