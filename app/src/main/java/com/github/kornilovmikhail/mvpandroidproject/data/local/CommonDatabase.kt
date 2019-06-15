package com.github.kornilovmikhail.mvpandroidproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question
import com.github.kornilovmikhail.mvpandroidproject.data.entity.TypeConverter
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.AnswerDao
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.QuestionDao

@Database(entities = [Question::class, Answer::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class CommonDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao() : AnswerDao
}