package io.reyurnible.android.workrise.presentation.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.presentation.common.setContentFragment
import java.util.*

class FormActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, FormActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)
        savedInstanceState ?: setContentFragment(R.id.containerLayout, FormFragment.createInstance(YearMonthDay(Date())))
    }

}
