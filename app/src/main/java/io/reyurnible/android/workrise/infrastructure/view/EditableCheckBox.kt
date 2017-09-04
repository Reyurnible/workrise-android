package io.reyurnible.android.workrise.infrastructure.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.Checkable
import android.widget.RelativeLayout
import android.widget.TextView
import io.reyurnible.android.workrise.R
import kotlinx.android.synthetic.main.view_editable_checkbox.view.*

/**
 * EditText + CheckBox
 */
class EditableCheckBox : RelativeLayout, Checkable {
    var editorActionListener: ((v: EditableCheckBox, editable: TextView, actionId: Int, event: KeyEvent) -> Boolean)? = null
        set(value) {
            field = value
            value?.let {
                contentEditText.setOnEditorActionListener { textView, actionId, event ->
                    it.invoke(this@EditableCheckBox, textView, actionId, event)
                }
            } ?: let {
                contentEditText.setOnEditorActionListener(null)
            }
        }

    private var checked: Boolean = false

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        inflate(context, R.layout.view_editable_checkbox, this)
        isChecked = false
        checkButton.setOnClickListener {
            toggle()
        }
    }

    override fun toggle() {
        isChecked = !isChecked
    }

    override fun isChecked(): Boolean = checked

    override fun setChecked(checked: Boolean) {
        this.checked = checked
        checkButton.setImageResource(if (checked) R.drawable.ic_check_box_checked else R.drawable.ic_check_box_unchecked)
    }

}
