package io.reyurnible.android.workrise.presentation.form

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.extensions.setContentFragment
import io.reyurnible.android.workrise.extensions.showAsStack
import io.reyurnible.android.workrise.extensions.toDisplay
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class FormActivity : AppCompatActivity(), HasSupportFragmentInjector {
    companion object;

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    private val date: YearMonthDay by bindDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)
        savedInstanceState ?: setContentFragment(R.id.containerLayout, FormFragment.createInstance(date))
        toolbar.run {
            setSupportActionBar(this)
            showAsStack(this)
            title = date.toDisplay(this@FormActivity)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

}
