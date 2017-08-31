package io.reyurnible.android.workrise.presentation.top

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class TopPagerAdapter(
        fragmentManager: FragmentManager,
        var dates: List<Date>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = dates.size

    override fun getItem(position: Int): Fragment {

    }
}
