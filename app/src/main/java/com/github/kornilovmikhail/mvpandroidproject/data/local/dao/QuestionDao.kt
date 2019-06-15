package com.github.kornilovmikhail.mvpandroidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.entity.QuestionAndAllAnswers

import io.reactivex.Single

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(questions: List<Question>)

    @Query("SELECT * FROM questions")
    fun getQuestions(): Single<List<Question>>

    @Query("SELECT * FROM questions")
    fun getQuestionsAndAnswers(): Single<List<QuestionAndAllAnswers>>

    @Query("DELETE FROM questions")
    fun deleteQuestions()
}