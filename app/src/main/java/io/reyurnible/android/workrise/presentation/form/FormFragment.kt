package io.reyurnible.android.workrise.presentation.form

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.domain.repository.param.FormEditingParam
import io.reyurnible.android.workrise.presentation.common.childList
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
        presenter.initialize(this, date)
        // Presenter Injection
        initializeView()
    }

    override fun onDestroyView() {
        presenter.release()
        super.onDestroyView()
    }

    private fun initializeView() {
        saveButton.setOnClickListener {
            val todoContent = FormEditingParam.CheckList("やること", todoContainerLayout.checkItems)
            val otherContent = FormEditingParam.Text("その他、メモ", otherContainerLayout.childList().map { it as EditText }.map { it.text.toString() }.first())
            presenter.clickSave(formContent = listOf(todoContent, otherContent))
        }
    }

    override fun transitionReportDetails(report: Report) {
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
