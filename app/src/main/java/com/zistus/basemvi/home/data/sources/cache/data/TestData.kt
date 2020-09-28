package com.zistus.basemvi.home.data.sources.cache.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zistus.basemvi.utils.Labels

@Entity(
    tableName = Labels.Database.TEST_DATA
)
data class TestData(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "first")
    val first: String,

    @ColumnInfo(name = "last")
    val last: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "created")
    val created: String,

    @ColumnInfo(name = "balance")
    val balance: String

)