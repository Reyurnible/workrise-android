package io.reyurnible.android.workrise.presentation.reportsetting

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import io.reyurnible.android.workrise.R

/**
 * Dialog Fragment
 */
class FormSettingEditDialog : DialogFragment() {
    companion object {
        fun createInstance(): FormSettingEditDialog =
                FormSettingEditDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(context)
                .setView(kotlin.run {
                    LayoutInflater.from(context).inflate()
                })
                .setPositiveButton(R.string.form_setting_dialog_btn_create)
                .create()
}
