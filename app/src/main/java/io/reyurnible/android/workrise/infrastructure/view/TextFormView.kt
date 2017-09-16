package io.reyurnible.android.workrise.infrastructure.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Form
import kotlinx.android.synthetic.main.view_form_text.view.*

/**
 * TextFormView
 */
class TextFormView : RelativeLayout {
    var form: Form.Text? = null
        set(value) {
            field = value
            inflateForm(value)
        }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        inflate(context, R.layout.view_form_text, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        form = null
    }

    private fun inflateForm(form: Form.Text?) {
        titleText.text = form?.title
        contentText.text = form?.content
    }

}