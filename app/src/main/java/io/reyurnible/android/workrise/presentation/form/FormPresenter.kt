package io.reyurnible.android.workrise.presentation.form

import io.reyurnible.android.workrise.usecase.CreateReportUseCase
import javax.inject.Inject

class FormPresenter
@Inject constructor(
        private val createReportUseCase: CreateReportUseCase
) {

    fun clickSave() {

    }

    interface FormView {

    }
}
