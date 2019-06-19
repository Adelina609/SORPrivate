package com.github.adelina609.stackoverrelations.data.entity

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
    var answer: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("likes")
    var likes: Int,

    @SerializedName("dislikes")
    var dislikes: Int,

    @SerializedName("question")
    @ColumnInfo(name = "question_id")
    var question_id : Int
){
    override fun toString(): String {
        return "$id,$answer,$email,$likes,$dislikes,$question_id;"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Answer

        if (id != other.id) return false
        if (answer != other.answer) return false
        if (email != other.email) return false
        if (question_id != other.question_id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + answer.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + question_id
        return result
    }


}