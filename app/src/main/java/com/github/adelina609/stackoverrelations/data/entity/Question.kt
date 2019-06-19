package com.github.adelina609.stackoverrelations.data.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "questions")
class Question(
    @ColumnInfo(index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("email")
    var email: String
) {
    override fun toString(): String {
        return "$id,$title,$description,$email,"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (id != other.id) return false
        if (title != other.title) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }


}