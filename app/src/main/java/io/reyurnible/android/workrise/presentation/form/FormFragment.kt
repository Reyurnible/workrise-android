package io.reyurnible.android.workrise.presentation.form

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.param.CheckItemEditingParam
import io.reyurnible.android.workrise.domain.repository.param.FormEditingParam
import io.reyurnible.android.workrise.extensions.childList
import io.reyurnible.android.workrise.infrastructure.view.EditableCheckBoxContainerLayout
import kotlinx.android.synthetic.main.form_fragment.*
import javax.inject.Inject

/**
 * Form page
 */
class FormFragment : Fragment(), FormPresenter.FormView {
    companion object;

    private val date: YearMonthDay by bindDate()

    @Inject lateinit var presenter: FormPresenter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.form_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Presenter Injection
        initializeView()
        presenter.initialize(this, date)
    }

    override fun onDestroyView() {
        presenter.release()
        super.onDestroyView()
    }

    private fun initializeView() {
        saveButton.setOnClickListener {
            val formContent = formContainerLayout.childList().map {
                when (it) {
                    is EditableCheckBoxContainerLayout -> FormEditingParam.CheckList(it.tag as String, it.checkItems)
                    is EditText -> FormEditingParam.Text(it.tag as String, it.text.toString())
                    else -> null
                }
            }.filterNotNull()
            presenter.clickSave(formContent = formContent)
        }
    }

    override fun setReportSetting(setting: ReportSetting) {
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        formContainerLayout.apply {
            removeAllViews()
            setting.formSettings.forEach {
                addView(TextView(ContextThemeWrapper(activity, R.style.AppTheme_Widget_Form_TitleSection)), layoutParams)
                addView(when (it.type) {
                    FormSetting.FormType.CheckList -> EditableCheckBoxContainerLayout(ContextThemeWrapper(activity, R.style.AppTheme_Widget_Form_CheckBoxContainer))
                    FormSetting.FormType.Text -> EditText(ContextThemeWrapper(activity, R.style.AppTheme_Widget_Form_Text))
                }.apply {
                    tag = it.title
                }, layoutParams)
            }
        }
    }

    override fun setReport(report: Report) {
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        formContainerLayout.apply {
            removeAllViews()
            report.content.forEach {
                addView(TextView(ContextThemeWrapper(activity, R.style.AppTheme_Widget_Form_TitleSection)), layoutParams)
                addView(when (it) {
                    is Form.CheckList -> EditableCheckBoxContainerLayout(ContextThemeWrapper(activity, R.style.AppTheme_Widget_Form_CheckBoxContainer)).apply {
                        checkItems = it.content.map { CheckItemEditingParam(it.content, it.checked) }
                    }
                    is Form.Text -> EditText(ContextThemeWrapper(activity, R.style.AppTheme_Widget_Form_Text)).apply {
                        setText(it.content)
                    }
                }.apply {
                    tag = it.title
                }, layoutParams)
            }
        }
    }

    override fun showReportDetails(report: Report) {
        TODO("not implemented")
    }

    override fun finish() {
        activity.finish()
    }

    override fun showErrorDialog(error: Throwable) {
        error.printStackTrace()
    }

    override fun showLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
