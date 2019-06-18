package com.github.adelina609.stackoverrelations.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.adelina609.stackoverrelations.App
import com.github.adelina609.stackoverrelations.R
import com.github.adelina609.stackoverrelations.data.entity.Notification
import com.github.adelina609.stackoverrelations.di.question.component.DaggerQuestionComponent
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.presenter.NotificationPresenter
import com.github.adelina609.stackoverrelations.ui.list.OnScrollListener
import kotlinx.android.synthetic.main.fragment_notifications.*
import javax.inject.Inject

class NotificationFragment : MvpAppCompatFragment(), NotificationView {
    @Inject
    @InjectPresenter
    lateinit var notifPresenter: NotificationPresenter

    @ProvidePresenter
    fun getPresenter(): NotificationPresenter = notifPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerQuestionComponent
            .builder()
            .appComponent(App.getAppComponents())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_notifications, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        notifPresenter.getNotifications()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun displayNotifications(list: List<Notification>) {
        if (rv_notif.adapter == null) {
            rv_notif.adapter = NotificationAdapter(list) {
                notifPresenter.notifClick(it)
            }
        }
        (rv_notif.adapter as NotificationAdapter).submitList(list)
    }

    private fun setupViews() {
        rv_notif.layoutManager = LinearLayoutManager(context)
        rv_notif.addOnScrollListener(OnScrollListener(
            rv_notif.layoutManager as LinearLayoutManager
        ) {
            notifPresenter.getNotifications()
        })
    }

    override fun displayError() {
        Toast.makeText(context, R.string.error_notif, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        notif_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        notif_progressBar.visibility = ProgressBar.INVISIBLE
    }

}