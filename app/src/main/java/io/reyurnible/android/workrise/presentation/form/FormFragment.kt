package io.reyurnible.android.workrise.presentation.form

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import io.reyurnible.android.workrise.infrastructure.view.EditableCheckBox
import io.reyurnible.android.workrise.presentation.common.isLastIndexChild
import io.reyurnible.android.workrise.presentation.common.nextOrNullView
import kotlinx.android.synthetic.main.form_fragment.*
import kotlinx.android.synthetic.main.view_editable_checkbox.view.*
import javax.inject.Inject


/**
 * Form page
 */
class FormFragment : Fragment() {
    companion object;

    private val date: YearMonthDay by bindDate()

    @Inject lateinit var presenter: FormPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.form_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Presenter Injection
        initializeView()
    }

    private fun initializeView() {
        todoContainerLayout.apply {
            addView(createEditableCheckBox(), LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        saveButton.setOnClickListener {
            presenter.clickSave(

            )
        }
    }

    private fun createEditableCheckBox(): EditableCheckBox = EditableCheckBox(activity).apply {
        // EditText Setting
        editorActionListener = this@FormFragment::actionCheckableEditText
    }

    private fun actionCheckableEditText(view: EditableCheckBox, textView: TextView, actionId: Int, keyEvent: KeyEvent): Boolean {
        Log.d(this@FormFragment.javaClass.simpleName, "setOnEditorActionListener(${actionId}, ${keyEvent})")
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
