//package com.github.kornilovmikhail.mvpandroidproject.ui.list.dialog
//
//import android.app.Dialog
//import android.os.Bundle
//import androidx.appcompat.app.AlertDialog
//import android.widget.EditText
//import com.arellomobile.mvp.MvpAppCompatDialogFragment
//import com.arellomobile.mvp.presenter.InjectPresenter
//import com.arellomobile.mvp.presenter.ProvidePresenter
//import com.example.stackoverrelations.R
//
//import com.github.kornilovmikhail.mvpandroidproject.data.repository.PaginationPreferencesRepo
//import com.github.kornilovmikhail.mvpandroidproject.presenter.PaginationDialogPresenter
//
//class PaginationDialog : MvpAppCompatDialogFragment(), PaginationDialogView {
//
//    @InjectPresenter
//    lateinit var paginationPresenter: PaginationDialogPresenter
//
//    @ProvidePresenter
//    fun initPresenter() = PaginationDialogPresenter(PaginationPreferencesRepo())
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val v = activity?.layoutInflater?.inflate(R.layout.pagination_dialog, null)
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            builder.setMessage(R.string.label_dialog)
//                .setView(v)
//                .setPositiveButton(
//                    R.string.label_submit
//                ) { _, _ ->
//                    val pagination = v?.findViewById<EditText>(R.id.edt_pagination)?.text.toString()
//                    if (pagination != "") {
//                        paginationPresenter.setPagination(
//                            pagination.toInt()
//                        )
//                    }
//                    paginationPresenter.dismiss()
//                }
//                .setNegativeButton(
//                    R.string.label_cancel
//                ) { _, _ ->
//                    paginationPresenter.dismiss()
//                }
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//
//    override fun hideDialog() {
//        dialog.dismiss()
//    }
//}
