package io.reyurnible.android.workrise.presentation.report

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.presentation.form.FormActivity
import io.reyurnible.android.workrise.presentation.form.createIntent
import kotlinx.android.synthetic.main.report_fragment.*
import kotlin.properties.Delegates

class ReportFragment : Fragment(), ReportPresenter.ReportView {

    companion object;

    private val date: YearMonthDay by bindDate()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.report_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButton.setOnClickListener {
            startActivity(FormActivity.createIntent(activity, date))
        }
    }

}
