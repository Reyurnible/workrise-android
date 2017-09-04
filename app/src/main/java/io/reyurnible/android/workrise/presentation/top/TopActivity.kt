package io.reyurnible.android.workrise.presentation.top

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import kotlinx.android.synthetic.main.top_activity.*
import java.util.*

class TopActivity : AppCompatActivity(), TopPresenter.TopView {

    private lateinit var pagerAdapter: TopPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_activity)
        pagerAdapter = TopPagerAdapter(supportFragmentManager, applicationContext, dates = emptyList<YearMonthDay>())
        // Setup Views
        viewPager.adapter = pagerAdapter
        tabLayout.setUpWithViewPager(viewPager)

        pagerAdapter.dates = createDates()
        pagerAdapter.notifyDataSetChanged()
    }

    fun createDates(): List<YearMonthDay> =
            (0 until 30).map {
                Calendar.getInstance().apply {
                    time = Date()
                    add(Calendar.DATE, -it)
                }
            }.map {
                YearMonthDay(it)
            }


}
