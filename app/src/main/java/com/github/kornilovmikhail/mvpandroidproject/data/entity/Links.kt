package com.github.kornilovmikhail.mvpandroidproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Links(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("reddit") val linkReddit: String?,
    @SerializedName("article") val linkArticle: String?,
    @SerializedName("wikipedia") val linkWikipedia: String?
)
