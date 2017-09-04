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

class ReportFragment : Fragment() {

    private object Keys {
        const val date = "date"
    }

    companion object {
        fun createInstance(date: YearMonthDay): ReportFragment = ReportFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Keys.date, date)
            }
        }
    }

    private var date: YearMonthDay by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            date = getParcelable(Keys.date)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.report_fragment, container, false).apply {

            }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButton.setOnClickListener {
            startActivity(FormActivity.createIntent(activity, date))
        }
    }

}
