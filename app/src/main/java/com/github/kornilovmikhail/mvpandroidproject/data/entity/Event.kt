package com.github.kornilovmikhail.mvpandroidproject.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "eventData")
data class Event(

    @ColumnInfo(index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("event_date_unix")
    @ColumnInfo(name = "event_date")
    val eventDate: Long,

    @SerializedName("flight_number")
    @ColumnInfo(name = "flight_name")
    val flightNumber: Int?,

    @SerializedName("details")
    @ColumnInfo(name = "detail")
    val details: String,

    @SerializedName("links")
    @Embedded(prefix = "links")
    var links: Links
)
