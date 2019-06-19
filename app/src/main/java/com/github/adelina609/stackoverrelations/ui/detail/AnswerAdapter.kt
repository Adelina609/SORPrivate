package com.github.adelina609.stackoverrelations.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Answer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

class AnswerAdapter(
        private var answers: List<Answer>
) : RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): AnswerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.answer_list_item, parent, false)
        return AnswerHolder(view)
    }

    override fun getItemCount(): Int = answers.size

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.bind(answers[position].answer)
    }

    fun updateDataSet(list: List<Answer>) {
        answers = list
        notifyDataSetChanged()
    }

    class AnswerHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(answerName: String) {
            containerView.tv_list_item_name.text = answerName
        }
    }
}
