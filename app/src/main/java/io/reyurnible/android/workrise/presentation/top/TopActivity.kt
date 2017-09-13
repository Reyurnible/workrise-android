package io.reyurnible.android.workrise.presentation.top

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import kotlinx.android.synthetic.main.top_activity.*
import javax.inject.Inject

class TopActivity : AppCompatActivity(), TopPresenter.TopView {

    @Inject lateinit var presenter: TopPresenter
    private lateinit var pagerAdapter: TopPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_activity)
        pagerAdapter = TopPagerAdapter(supportFragmentManager, applicationContext, dates = emptyList<YearMonthDay>())
        // Setup Views
        viewPager.adapter = pagerAdapter
        tabLayout.setUpWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter.selectedPage(position)
            }
        })
        presenter.initialize(this)
    }

    override fun onDestroy() {
        presenter.release()
        super.onDestroy()
    }

    override fun setDailyReportList(dailyReportList: List<Pair<YearMonthDay, Report?>>) {
        pagerAdapter.dates = dailyReportList.map { it.first }
        pagerAdapter.notifyDataSetChanged()
    }

    override fun setCurrentPosition(position: Int) {
        viewPager.currentItem = position
    }

}
