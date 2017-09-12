package io.reyurnible.android.workrise.infrastructure.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.widget.LinearLayout
import android.widget.TextView
import io.reyurnible.android.workrise.domain.repository.param.CheckItemEditingParam
import io.reyurnible.android.workrise.presentation.common.childList
import io.reyurnible.android.workrise.presentation.common.isLastIndexChild
import io.reyurnible.android.workrise.presentation.common.nextOrNullView
import kotlinx.android.synthetic.main.view_editable_checkbox.view.*

class EditableCheckBoxContainerLayout : LinearLayout {

    var checkItems: List<CheckItemEditingParam>
        set(value) {
            removeAllViews()
            value.forEach(this::addCheckItem)
        }
        get() = childList()
                .map { it as EditableCheckBox }
                .map {
                    if (it.content.isNullOrBlank()) null
                    else CheckItemEditingParam(checkNotNull(it.content), it.isChecked)
                }.filterNotNull()

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        orientation = LinearLayout.VERTICAL
        // 最初に空のアイテムを1つだけ入れて、初期化
        checkItems = listOf(CheckItemEditingParam("", false))
    }

    fun addCheckItem(checkItem: CheckItemEditingParam) {
        addView(createEditableCheckBox().apply {
            isChecked = checkItem.checked
            content = checkItem.content
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
    }

    private fun createEditableCheckBox(): EditableCheckBox = EditableCheckBox(context).apply {
        // EditText Setting
        editorActionListener = this@EditableCheckBoxContainerLayout::actionCheckableEditText
    }

    private fun actionCheckableEditText(view: EditableCheckBox, textView: TextView, actionId: Int, keyEvent: KeyEvent): Boolean {
        Log.d(this@EditableCheckBoxContainerLayout.javaClass.simpleName, "setOnEditorActionListener(${actionId}, ${keyEvent})")
        //イベントを取得するタイミングには、ボタンが押されてなおかつエンターキーだったときを指定
        if (keyEvent.action == KeyEvent.ACTION_DOWN) {
            if (textView.text.isNullOrBlank()) {
                // 改行させないので、どのみちスルーする
                return true
            }
            (view.parent as? LinearLayout)?.let { container ->
                if (container.isLastIndexChild(view)) {
                    // 最後の場合
                    container.addView(createEditableCheckBox().apply {
                        this.contentEditText.apply {
                            isFocusableInTouchMode = true
                            requestFocus()
                            setSelection(0)
                        }
                    }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                } else {
                    // それ以外の場合、次のViewにFocusを移す
                    (container.nextOrNullView(view) as? EditableCheckBox)?.apply {
                        this.contentEditText.apply {
                            isFocusableInTouchMode = true
                            requestFocus()
                            setSelection(0)
                        }
                    }
                }
                return true
            }
            return false
        } else {
            return false
        }
    }

}
