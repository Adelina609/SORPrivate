package com.github.adelina609.stackoverrelations.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.adelina609.stackoverrelations.data.entity.Answer

import io.reactivex.Single

@Dao
interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnswers(answers: List<Answer>)

    @Query("SELECT * FROM answers")
    fun getAnswers(): Single<List<Answer>>
}