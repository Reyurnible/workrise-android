package io.reyurnible.android.workrise.domain.model.identifier

import io.reyurnible.android.workrise.domain.model.value.YearMonthDay

/**
 * 日報のID
 */
data class ReportId(override val value: YearMonthDay) : Identifier<YearMonthDay>(value)
