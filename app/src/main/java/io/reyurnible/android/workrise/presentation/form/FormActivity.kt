package io.reyurnible.android.workrise.presentation.form

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.presentation.common.setContentFragment
import io.reyurnible.android.workrise.presentation.common.showAsStack
import io.reyurnible.android.workrise.presentation.common.toDisplay
import kotlinx.android.synthetic.main.form_activity.*
import kotlinx.android.synthetic.main.layout_header.*

class FormActivity : AppCompatActivity() {
    companion object;

    private val date: YearMonthDay by bindDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)
        savedInstanceState ?: setContentFragment(R.id.containerLayout, FormFragment.createInstance(date))
        toolbar.run {
            setSupportActionBar(this)
            showAsStack(this)
            title = date.toDisplay(this@FormActivity)
        }
    }

}
