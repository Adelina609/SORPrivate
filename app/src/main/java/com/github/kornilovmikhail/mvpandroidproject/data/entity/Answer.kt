package com.github.kornilovmikhail.mvpandroidproject.data.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "answers", foreignKeys = [ForeignKey(entity = Question::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("question_id"),
    onDelete = ForeignKey.CASCADE)])
class Answer(
    @ColumnInfo(index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("answer")
    val answer: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("likes")
    val likes: Int,

    @SerializedName("dislikes")
    val dislikes: Int,

    @SerializedName("question")
//    @Embedded(prefix = "question")
    @ColumnInfo(name = "question_id")
    var question_id : Int
){
    override fun toString(): String {
        return "$id,$answer,$email,$likes,$dislikes,$question_id;"
    }
}