package com.github.kornilovmikhail.mvpandroidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM eventData")
    fun getEvents(): Single<List<Event>>

    @Query("DELETE FROM eventData")
    fun deleteEvents()
}
