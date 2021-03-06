package io.reyurnible.android.workrise.infrastructure.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.identifier.CheckItemId
import kotlinx.android.synthetic.main.view_form_checklist.view.*

/**
 * ChecklistFormView
 */
class ChecklistFormView : RelativeLayout {
    var form: Form.CheckList? = null
        set(value) {
            field = value
            inflateForm(value)
        }

    var itemCheckedChangeListener: ((CheckItemId, Boolean) -> Unit)? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        inflate(context, R.layout.view_form_checklist, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        form = null
    }

    private fun inflateForm(form: Form.CheckList?) {
        titleText.text = form?.title
        contentContainerLayout.removeAllViews()
        form?.let { _ ->
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            form.content.map { checkList ->
                CheckBox(context).apply {
                    isChecked = checkList.checked
                    text = checkList.content
                    setOnCheckedChangeListener(this@ChecklistFormView::onItemCheckedChange)
                    // タグとしてチェックリストを差し込んでおく
                    tag = checkList.id
                }
            }.forEach { view ->
                contentContainerLayout.addView(view, layoutParams)
            }
        }
    }

    private fun onItemCheckedChange(view: CompoundButton, checked: Boolean) {
        (view.tag as? CheckItemId)?.let { itemId ->
            itemCheckedChangeListener?.invoke(itemId, checked)
        }
    }
}
