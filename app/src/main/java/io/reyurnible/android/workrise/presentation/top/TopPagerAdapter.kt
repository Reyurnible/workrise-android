package io.reyurnible.android.workrise.presentation.top

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.presentation.report.ReportFragment
import io.reyurnible.android.workrise.presentation.report.createInstance

class TopPagerAdapter(
        fragmentManager: FragmentManager,
        var dates: List<YearMonthDay>
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int = dates.size

    override fun getItem(position: Int): Fragment = ReportFragment.createInstance(dates[position])
}
