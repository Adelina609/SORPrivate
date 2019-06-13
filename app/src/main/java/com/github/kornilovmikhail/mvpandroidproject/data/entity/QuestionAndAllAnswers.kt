package com.github.kornilovmikhail.mvpandroidproject.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Answer
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Question


class QuestionAndAllAnswers {
    @Embedded
    var question: Question? = null
    @Relation(parentColumn = "id", entityColumn = "question_id", entity = Answer::class)
    var answers: List<Answer>? = null

}
