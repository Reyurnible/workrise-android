package io.reyurnible.android.workrise.presentation.reportsetting

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import kotlinx.android.synthetic.main.report_setting_fragment.*
import javax.inject.Inject

/**
 * 日報設定画面
 */
class ReportSettingFragment : Fragment(),
        ReportSettingPresenter.ReportSettingView,
        FormSettingAdapter.OnFormSettingAdapterListener {
    companion object;

    @Inject lateinit var presenter: ReportSettingPresenter
    private lateinit var adapter: FormSettingAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.report_setting_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        presenter.initialize(this)
    }

    override fun onDestroyView() {
        presenter.release()
        super.onDestroyView()
    }

    private fun initializeView() {
        adapter = FormSettingAdapter(context, this@ReportSettingFragment)
        formSettingList.adapter = adapter
        addButton.setOnClickListener {
            presenter.clickAddFormSetting()
        }
    }

    override fun setReportSetting(reportSetting: ReportSetting?) {
        adapter.listener
    }

    override fun showErrorDialog(error: Throwable) {

    }

    override fun onItemClickListener(formSetting: FormSetting) {

    }

    override fun onItemEditClickListener(formSetting: FormSetting) {

    }

    override fun onItemDeleteClickListener(formSetting: FormSetting) {

    }

}
