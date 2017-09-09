package io.reyurnible.android.workrise.usecase

import io.reactivex.Single
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.repository.param.ReportEditingParam

/**
 * Creation Report UseCase
 */
interface CreateReportUseCase {
    /**
     * @param param creation report parameters
     */
    fun create(param: ReportEditingParam): Single<Report>
}
