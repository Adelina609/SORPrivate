package com.github.adelina609.stackoverrelations.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.adelina609.stackoverrelations.data.local.dao.EventDao
import com.github.adelina609.stackoverrelations.data.entity.Event
import com.github.adelina609.stackoverrelations.data.entity.Links

@Database(entities = [Event::class, Links::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
