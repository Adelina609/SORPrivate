package com.github.adelina609.stackoverrelations.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.adelina609.stackoverrelations.data.entity.Answer
import com.github.adelina609.stackoverrelations.data.entity.Question
import com.github.adelina609.stackoverrelations.data.entity.TypeConverter
import com.github.adelina609.stackoverrelations.data.local.dao.AnswerDao
import com.github.adelina609.stackoverrelations.data.local.dao.QuestionDao

@Database(entities = [Question::class, Answer::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class CommonDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao() : AnswerDao
}