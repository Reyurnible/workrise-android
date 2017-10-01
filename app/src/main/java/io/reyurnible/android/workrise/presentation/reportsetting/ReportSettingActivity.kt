package io.reyurnible.android.workrise.presentation.reportsetting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.extensions.setContentFragment
import io.reyurnible.android.workrise.extensions.showAsStack
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class ReportSettingActivity : AppCompatActivity(), HasSupportFragmentInjector {
    companion object;

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_setting_activity)
        savedInstanceState ?: setContentFragment(R.id.containerLayout, ReportSettingFragment.createInstance())
        toolbar.run {
            setSupportActionBar(this)
            showAsStack(this)
            title = getString(R.string.report_setting_title)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

}
