package io.reyurnible.android.workrise.presentation.top

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.extensions.setContentFragment
import kotlinx.android.synthetic.main.layout_header.*

class TopActivity : AppCompatActivity() {
    companion object;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_activity)
        // Setup Views
        toolbar.run {
            setSupportActionBar(this)
        }
        savedInstanceState ?: setContentFragment(R.id.containerLayout, TopFragment.createInstance())
    }

}
