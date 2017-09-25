package io.reyurnible.android.workrise.domain.service

import android.content.Context
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting

/**
 * ReportSetting default provider
 */
object ReportSettingProvider {

    fun createDefaultReportSetting(context: Context): ReportSetting =
            ReportSetting(formSettings = createDefaultFormSettings(context))

    private fun createDefaultFormSettings(context: Context): List<FormSetting> =
            listOf(
                    FormSetting(
                            title = context.getString(R.string.form_setting_title_default_todo),
                            type = FormSetting.FormType.CheckList
                    ),
                    FormSetting(
                            title = context.getString(R.string.form_setting_title_default_memo),
                            type = FormSetting.FormType.Text
                    ))
}
