package com.github.adelina609.stackoverrelations.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "notifications")
class Notification
    (
    @ColumnInfo(index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email :String,

    @SerializedName("notification")
    var notification: String,

    @SerializedName("photo")
    var photo: String,

    @SerializedName("question_id")
    var question_id: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Notification

        if (id != other.id) return false
        if (question_id != other.question_id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + question_id
        return result
    }

    override fun toString(): String {
        return "Notification(id=$id, username='$username', photo='$photo', question_id=$question_id)"
    }
}