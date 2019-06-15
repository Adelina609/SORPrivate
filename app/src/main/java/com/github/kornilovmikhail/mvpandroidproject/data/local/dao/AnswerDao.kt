package com.github.kornilovmikhail.mvpandroidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.QuestionAndAllAnswers

import io.reactivex.Single

@Dao
interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnswers(answers: List<Answer>)

    @Query("SELECT * FROM answers")
    fun getAnswers(): Single<List<Answer>>

//    @Query("SELECT * FROM answers")
//    fun getAnswersAndAnswers(): Single<List<QuestionAndAllAnswers>>
//
//    @Query("DELETE FROM answers")
//    fun deleteAnswers()
}