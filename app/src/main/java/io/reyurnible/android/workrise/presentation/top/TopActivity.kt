package io.reyurnible.android.workrise.presentation.top

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.extensions.toVisible
import io.reyurnible.android.workrise.presentation.reportsetting.ReportSettingActivity
import io.reyurnible.android.workrise.presentation.reportsetting.createInstance
import kotlinx.android.synthetic.main.layout_header.*
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
        toolbar.run {
            setSupportActionBar(this)
        }
        viewPager.apply {
            adapter = pagerAdapter
            offscreenPageLimit = 2
        }
        tabLayout.setUpWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter.changePage(position)
            }
        })
        backPositionButton.setOnClickListener {
            presenter.clickBackToday()
        }
        presenter.initialize(this)
    }

    override fun onDestroy() {
        presenter.release()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                R.id.top_menu_setting -> {
                    startActivity(ReportSettingActivity.createInstance(this))
                    true
                }
                else -> false
            }

    override fun setDailyReportList(dailyReportList: List<Pair<YearMonthDay, Report?>>) {
        pagerAdapter.dates = dailyReportList.map { it.first }
        pagerAdapter.notifyDataSetChanged()
    }

    override fun setCurrentPosition(position: Int) {
        viewPager.setCurrentItem(position, false)
    }

    override fun setCurrentPositionStatus(isToday: Boolean) {
        backPositionButton.visibility = isToday.not().toVisible()
    }

}
