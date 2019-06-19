package com.github.adelina609.stackoverrelations.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Question
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.view.*

class QuestionAdapter(
    private val questions: List<Question>,
    private val questionLambda: (Int) -> Unit
) : ListAdapter<Question, QuestionAdapter.QuestionHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): QuestionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return QuestionHolder(view)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(questions[position].title, questions[position].description)
        val id = questions[position].id
        holder.itemView.setOnClickListener {
            questionLambda.invoke(id)
        }
    }

    override fun submitList(list: List<Question>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem == newItem

    }

    class QuestionHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(questionName: String, questionDesc: String) {
            containerView.tv_list_item_name.text = questionName
            containerView.tv_list_item_desc.text = questionDesc
        }
    }
}
