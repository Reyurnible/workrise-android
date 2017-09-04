package io.reyurnible.android.workrise.domain.model.value

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * YearMonthDay(yyyy-MM-dd) format date value object.
 */
@Parcelize
data class YearMonthDay(
        val year: Int,
        val month: Int,
        val day: Int
) : Parcelable {
    companion object {
        private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        fun createFromString(formattedText: String): YearMonthDay = YearMonthDay(simpleDateFormat.parse(formattedText))
    }

    constructor(calendar: Calendar) : this(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH) + 1,
            day = calendar.get(Calendar.DAY_OF_MONTH)
    )

    constructor(date: Date) : this(calendar = Calendar.getInstance().apply { time = date })

    override fun toString(): String = String.format("%04d-%02d-%02d", year, month, day)
}
