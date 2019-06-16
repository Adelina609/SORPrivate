package com.github.adelina609.stackoverrelations.data.entity

import androidx.room.Embedded
import androidx.room.Relation


class QuestionAndAllAnswers {
    @Embedded
    var question: Question? = null
    @Relation(parentColumn = "id", entityColumn = "question_id", entity = Answer::class)
    var answers: List<Answer>? = null

}
