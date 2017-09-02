package io.reyurnible.android.workrise.presentation.form

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Form page
 */
class FormFragment : Fragment() {
    private object Keys {
        const val date = "date"
    }

    companion object {
        fun createInstance(date: Date): FormFragment = FormFragment().apply {
            arguments = Bundle().apply {
                putLong(Keys.date, date.time)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
