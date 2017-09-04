package io.reyurnible.android.workrise.domain.model.value

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

/**
 * YearMonthDay(yyyy-MM-dd) format date value object.
 */
data class YearMonthDay(
        val year: Int,
        val month: Int,
        val day: Int
) : Parcelable {
    companion object CREATOR : Parcelable.Creator<YearMonthDay> {
        private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        fun createFromString(formattedText: String): YearMonthDay = YearMonthDay(simpleDateFormat.parse(formattedText))

        override fun createFromParcel(parcel: Parcel): YearMonthDay = YearMonthDay(parcel)

        override fun newArray(size: Int): Array<YearMonthDay?> = arrayOfNulls(size)
    }

    constructor(parcel: Parcel) : this(
            year = parcel.readInt(),
            month = parcel.readInt(),
            day = parcel.readInt()
    )

    constructor(calendar: Calendar) : this(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH) + 1,
            day = calendar.get(Calendar.DAY_OF_MONTH)
    )

    constructor(date: Date) : this(calendar = Calendar.getInstance().apply { time = date })

    override fun toString(): String = String.format("%04d-%02d-%02d", year, month, day)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(year)
        parcel.writeInt(month)
        parcel.writeInt(day)
    }

    override fun describeContents(): Int = 0
}
