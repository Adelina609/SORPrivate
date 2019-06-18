package com.github.adelina609.stackoverrelations.ui.notification

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Notification
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notification_list_item.view.*

class NotificationAdapter(
    private val notifications: List<Notification>,
    private val notificationLambda: (Int) -> Unit
) :
    ListAdapter<Notification, NotificationAdapter.NotificationHolder>
        (NotificationDiffCallback()) {

    override fun submitList(list: List<Notification>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_item, parent, false)
        return NotificationHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        var uri : Uri = Uri.parse(notifications[position].photo)
        holder.bind(notifications[position].notification,uri)
        val id = notifications[position].id
        holder.itemView.setOnClickListener {
            notificationLambda.invoke(id)
        }
    }


    class NotificationDiffCallback : DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean = oldItem == newItem

    }

    class NotificationHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(notification: String, uri: Uri) {
            containerView.tv_notif.text = notification
            containerView.iv_photo_notif.setImageURI(uri)
        }
    }
}