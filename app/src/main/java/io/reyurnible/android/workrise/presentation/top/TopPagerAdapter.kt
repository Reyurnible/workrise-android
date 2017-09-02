package io.reyurnible.android.workrise.presentation.top

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.reyurnible.android.workrise.presentation.form.FormFragment
import java.util.*

class TopPagerAdapter(
        fragmentManager: FragmentManager,
        var dates: List<Date>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = dates.size

    override fun getItem(position: Int): Fragment = FormFragment()

    override fun getPageTitle(position: Int): CharSequence {
        return super.getPageTitle(position)
    }
}
