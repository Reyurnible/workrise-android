package io.reyurnible.android.workrise.presentation.report

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.infrastructure.view.ChecklistFormView
import io.reyurnible.android.workrise.infrastructure.view.TextFormView
import io.reyurnible.android.workrise.presentation.common.invisible
import io.reyurnible.android.workrise.presentation.common.visible
import io.reyurnible.android.workrise.presentation.form.FormActivity
import io.reyurnible.android.workrise.presentation.form.createIntent
import kotlinx.android.synthetic.main.report_fragment.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ReportFragment : Fragment(), ReportPresenter.ReportView {
    companion object;

    private val date: YearMonthDay by bindDate()
    override var report: Report? = null
        set(value) {
            field = value
            inflateReport(value)
        }

    @Inject lateinit var presenter: ReportPresenter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.report_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize(this, date)
    }

    override fun onDestroyView() {
        presenter.release()
        super.onDestroyView()
    }

    override fun showErrorDialog(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Report表示用のViewの作成
     */
    private fun inflateReport(report: Report?) {
        report?.run {
            createButton.invisible()
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            content.map { form ->
                when (form) {
                    is Form.CheckList -> ChecklistFormView(context).also { view -> view.form = form }
                    is Form.Text -> TextFormView(context).also { view -> view.form = form }
                }
            }.forEach { view ->
                reportContainerLayout.addView(view, layoutParams)
            }
        } ?: let {
            createButton.visible()
            createButton.setOnClickListener {
                startActivity(FormActivity.createIntent(activity, date))
            }
        }
    }

}
