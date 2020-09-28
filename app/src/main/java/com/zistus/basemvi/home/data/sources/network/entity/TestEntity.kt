package com.zistus.basemvi.home.data.sources.network.entity

import com.zistus.basemvi.home.data.sources.cache.data.TestData
import java.util.*

data class TestEntity(
    val id: String ?= null,
    val first: String ?= null,
    val last: String ?= null,
    val email: String ?= null,
    val address: String ?= null,
    val created: String ?= null,
    val balance: String ?=null
) {
    fun toData() = TestData(
        id = UUID.randomUUID().toString(),
        first = this.first?:"",
        last = this.last?:"",
        email = this.email?:"",
        address = this.address?:"",
        created = this.created?:"",
        balance = this.balance?:""
    )

    fun toDto() = TestDto(
        first, last, email, address, created, balance
    )
}