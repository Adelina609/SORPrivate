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
    private val answers: List<Answer>,
    private val answerLambda: (Int) -> Unit
) : ListAdapter<Answer, AnswerAdapter.AnswerHolder>(AnswerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): AnswerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return AnswerHolder(view)
    }

    override fun getItemCount(): Int = answers.size

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.bind(answers[position].answer)
        holder.itemView.setOnClickListener {
            answerLambda.invoke(position)
        }
    }

    override fun submitList(list: List<Answer>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    class AnswerDiffCallback : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean = oldItem.answer == newItem.answer

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean = oldItem == newItem

    }

    class AnswerHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(answerName: String) {
            containerView.tv_list_item_name.text = answerName
            containerView.tv_list_item_description.text = "smthhhhh"
        }
    }
}
