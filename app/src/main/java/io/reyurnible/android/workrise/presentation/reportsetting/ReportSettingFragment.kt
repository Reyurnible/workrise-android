package io.reyurnible.android.workrise.presentation.reportsetting

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import kotlinx.android.synthetic.main.report_setting_fragment.*
import javax.inject.Inject

/**
 * 日報設定画面
 */
class ReportSettingFragment : Fragment(),
        ReportSettingPresenter.ReportSettingView,
        FormSettingAdapter.OnFormSettingAdapterListener,
        FormSettingEditDialog.OnFormSettingEditDialogListener {
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
        adapter = FormSettingAdapter(context, this@ReportSettingFragment).apply {
            listener = this@ReportSettingFragment
        }
        formSettingList.adapter = adapter
        addButton.setOnClickListener {
            presenter.clickAddFormSetting()
        }
    }

    override fun setFormSettings(formSettings: List<FormSetting>?) {
        Log.d(ReportSettingFragment::class.java.simpleName, "setFormSettings(${formSettings?.size})")
        adapter.apply {
            clear()
            formSettings?.let(this::addAll)
        }
    }

    override fun onItemClicked(formSetting: FormSetting) {
        presenter.clickFormSettingItem(formSetting)
    }

    override fun onItemEditClicked(formSetting: FormSetting) {
        presenter.clickEditFormSetting(formSetting)
    }

    override fun onItemDeleteClicked(formSetting: FormSetting) {
        presenter.clickDeleteFormSetting(formSetting)
    }

    override fun showFormSettingEditDialog() {
        FormSettingEditDialog.createInstance().show(childFragmentManager, FormSettingEditDialog::class.java.simpleName)
    }

    override fun showErrorDialog(error: Throwable) {

    }

    override fun onFormSettingEditSubmitClicked(dialog: FormSettingEditDialog, value: FormSetting) {
        presenter.submitFormSetting(value)
    }

    override fun onFormSettingEditCancelClicked(dialog: FormSettingEditDialog) {

    }

}
